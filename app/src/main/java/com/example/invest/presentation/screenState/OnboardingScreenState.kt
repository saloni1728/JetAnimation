package com.example.invest.presentation.screenState

import androidx.compose.runtime.Immutable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.invest.domain.model.Card
import com.example.invest.domain.model.OnboardingUIModel
import com.example.invest.domain.model.Screen

@Immutable
data class OnboardingScreenState(
    val loading: Boolean = false,
    val onboardingData: OnboardingUIModel,
    val cardList: List<Card> = emptyList(),
    val cardCollapsedList: List<Boolean> = emptyList(),
    val screenHeightForAnimation: Dp = 400.dp
) {
    companion object {
        val Empty = OnboardingScreenState(
            onboardingData = OnboardingUIModel.preview
        )
    }
}