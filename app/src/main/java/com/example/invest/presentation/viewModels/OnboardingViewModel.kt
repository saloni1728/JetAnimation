package com.example.invest.presentation.viewModels

import androidx.lifecycle.viewModelScope
import com.example.invest.domain.model.EventHandler
import com.example.invest.domain.model.Events
import com.example.invest.domain.model.OnboardingUIModel
import com.example.invest.domain.model.handleResult
import com.example.invest.domain.usecases.OnboardingUseCase
import com.example.invest.presentation.events.OnboardingEvents
import com.example.invest.presentation.screenState.OnboardingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase
): EventHandler<OnboardingEvents>() {

    private val _screenState = MutableStateFlow(OnboardingScreenState.Empty)
    val screenState = _screenState.asStateFlow()

    override fun handleEvent(event: Events.UIEvent) {
        when (event) {
            is OnboardingEvents.FetchOnboardingData -> {
                fetchOnboardingData()
            }

            is OnboardingEvents.UpdateCardList -> {
                _screenState.update {
                    it.copy(
                        cardList = it.cardList.toMutableList().apply { this.add(screenState.value.onboardingData.cards[event.index]) },
                        cardCollapsedList = it.cardCollapsedList.toMutableList().apply { this.add(false) }
                    )
                }
            }

            is OnboardingEvents.UpdateCardCollapseState -> {
                _screenState.update {
                    it.copy(
                        cardCollapsedList = it.cardCollapsedList.toMutableList().apply { this[event.index] = event.isCollapsed }
                    )
                }
            }

            is OnboardingEvents.SetScreenHeight -> {
                _screenState.update { it.copy(screenHeightForAnimation = event.height) }
            }

            else -> {}
        }
    }

    private fun fetchOnboardingData() = viewModelScope.launch {
        onboardingUseCase.invoke(onStart = { _screenState.update { it.copy(loading = true) }})
            .handleResult(
                onSuccess = {
                    val data = it as OnboardingUIModel
                    _screenState.update { it.copy(onboardingData = data, loading = true) }
                },
                onError = {
                    _screenState.update { it.copy(loading = true) }
                }
            )
    }
}