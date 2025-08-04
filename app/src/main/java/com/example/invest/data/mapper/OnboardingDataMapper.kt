package com.example.invest.data.mapper

import com.example.invest.domain.dtos.OnboardingResponseDto
import com.example.invest.domain.model.Card
import com.example.invest.domain.model.Image
import com.example.invest.domain.model.OnboardingUIModel
import com.example.invest.domain.model.Toolbar
import com.example.invest.ui.theme.AppColors
import com.example.invest.utils.CommonUtils.toColorOrDefault

class OnboardingDataMapper(private val onboardingResponseDto: OnboardingResponseDto) {
    fun get(): OnboardingUIModel = onboardingResponseDto.data?.manualBuyEducationData?.let {
        val defaultAppColor = AppColors()
        return OnboardingUIModel(
            screenType = it.screenType ?: "",
            downLottie = it.ctaLottie ?: "",
            introTitle = it.introTitle ?: "",
            introSubtitle = it.introSubtitle ?: "",
            introSubtitleIcon = Image(
                imageUrl = it.introSubtitleIcon ?: "",
                description = "Intro Subtitle Icon"
            ),
            expandedCardTime = it.expandCardStayInterval ?: 3000L,
            collapsedCardTiltTime = it.collapseCardTiltInterval ?: 1000L,
            collapseExpandIntroInterval = it.collapseExpandIntroInterval ?: 500L,
            bottomToCenterTranslationInterval = it.bottomToCenterTranslationInterval ?: 1500L,
            shouldShowOnLandingPage = it.shouldShowOnLandingPage ?: false,
            shouldShowBeforeNavigating = it.shouldShowBeforeNavigating ?: true,
            saveButtonCta = Card(
                expandedText = it.saveButtonCta?.text ?: "",
                collapsedText = it.saveButtonCta?.text ?: "",
                image = Image(
                    imageUrl = it.saveButtonCta?.icon ?: "",
                    description = "Save Button Icon"
                ),
                colorScheme = it.saveButtonCta?.let { cta ->
                    AppColors(
                        background = cta.backgroundColorHex?.toColorOrDefault() ?: defaultAppColor.background,
                        text = defaultAppColor.text,
                        strokeStartColor = cta.strokeColorHex?.toColorOrDefault() ?: defaultAppColor.strokeStartColor,
                        strokeEndColor = cta.strokeColorHex?.toColorOrDefault() ?: defaultAppColor.strokeEndColor,
                    )
                } ?: defaultAppColor,
                icon = Image(
                    imageUrl = it.saveButtonCta?.icon ?: "",
                    description = "Save Button Icon"
                )
            ),
            cards = it.educationCardList?.map { card ->
                Card(
                    expandedText = card.expandStateText ?: "",
                    collapsedText = card.collapsedStateText ?: "",
                    image = Image(
                        imageUrl = card.image ?: "",
                        description = "Education Card Image"
                    ),
                    colorScheme = AppColors(
                        primary = defaultAppColor.primary,
                        background = card.backGroundColor?.toColorOrDefault() ?: defaultAppColor.background,
                        text = defaultAppColor.text,
                        textSecondary = defaultAppColor.textSecondary,
                        strokeStartColor = card.strokeStartColor?.toColorOrDefault() ?: defaultAppColor.strokeStartColor,
                        strokeEndColor = card.strokeEndColor?.toColorOrDefault() ?: defaultAppColor.strokeEndColor,
                        startGradient = card.startGradient?.toColorOrDefault() ?: defaultAppColor.startGradient,
                        endGradient = card.endGradient?.toColorOrDefault() ?: defaultAppColor.endGradient,
                        ctaColor = card.backGroundColor?.toColorOrDefault() ?: defaultAppColor.ctaColor
                    ),
                    icon = Image(
                        imageUrl = card.image ?: "",
                        description = "Education Card Icon"
                    )
                )
            } ?: emptyList(),
            toolbar = Toolbar(
                icon = Image(
                    imageUrl = it.toolBarIcon ?: "",
                    description = "Toolbar Icon"
                ),
                text = it.toolBarText ?: "",
                actionText = it.actionText ?: "",
                colorScheme = defaultAppColor
            )
        )
    } ?: OnboardingUIModel(
        cards = emptyList(),
        screenType = "",
        downLottie = "",
        introTitle = "",
        introSubtitle = ""
    )
}