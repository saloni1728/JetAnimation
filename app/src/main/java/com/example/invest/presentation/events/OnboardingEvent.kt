package com.example.invest.presentation.events

import androidx.compose.ui.unit.Dp
import com.example.invest.domain.model.Events

sealed class OnboardingEvents: Events.UIEvent() {
    data object FetchOnboardingData: OnboardingEvents()
    data class UpdateCardList(val index: Int): OnboardingEvents()
    data class UpdateCardCollapseState(val index: Int, val isCollapsed: Boolean): OnboardingEvents()
    data class SetScreenHeight(val height: Dp): OnboardingEvents()
}