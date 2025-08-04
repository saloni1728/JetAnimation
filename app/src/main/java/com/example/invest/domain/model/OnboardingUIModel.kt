package com.example.invest.domain.model

import androidx.compose.runtime.Immutable
import com.example.invest.ui.theme.AppColors
import com.example.invest.utils.CommonUtils.toColorOrDefault

@Immutable
data class OnboardingUIModel(
    val screenType: String,
    val downLottie: String,
    val introTitle: String,
    val introSubtitle: String,
    val introSubtitleIcon: Image? = null,
    val expandedCardTime: Long = 3000,
    val collapsedCardTiltTime: Long = 1000,
    val collapseExpandIntroInterval: Long = 500,
    val bottomToCenterTranslationInterval: Long = 1500,
    val shouldShowOnLandingPage: Boolean = false,
    val shouldShowBeforeNavigating: Boolean = false,
    val saveButtonCta: Card? = null,
    val cards: List<Card> = emptyList(),
    val toolbar: Toolbar? = null
) : Success.ViewState() {
    companion object {
        val preview = OnboardingUIModel(
            screenType = "SCREEN_B",
            downLottie = "https://cdn.myjar.app/BuyGoldFlow/manual_buy_education_cta_lottie.lottie",
            introTitle = "Welcome to",
            introSubtitle = "INSTANT SAVING",
            introSubtitleIcon = Image(
                imageUrl = "https://img.myjar.app/yboPYuxcnt49zki8Iw_vKoPDH-boET8q9ZY5FIA14X4/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/BuyGoldFlow/instantSaveLightningFlashIcon.webp",
                description = "Intro Subtitle Icon"
            ),
            expandedCardTime = 3000L,
            collapsedCardTiltTime = 1000L,
            collapseExpandIntroInterval = 500L,
            bottomToCenterTranslationInterval = 1500L,
            shouldShowOnLandingPage = false,
            shouldShowBeforeNavigating = true,
            saveButtonCta = Card(
                expandedText = "", // Not provided in JSON, so empty
                collapsedText = "", // Not provided in JSON, so empty
                image = Image(
                    imageUrl = "", // "icon" is null in saveButtonCta, so empty string
                    description = "Save Button Icon"
                ),
                colorScheme = AppColors(
                    text = "#FDF3D6".toColorOrDefault(),
                    background = "#272239".toColorOrDefault(),
                    strokeStartColor = "#272239".toColorOrDefault()
                ),
                icon = Image(
                    imageUrl = "", // saveButtonCta.icon is null
                    description = "Save Button Icon"
                )
            ),
            cards = listOf(
                Card(
                    expandedText = "Enjoy 100% control over your savings",
                    collapsedText = "Enjoy 100% control on savings",
                    image = Image(
                        imageUrl = "https://img.myjar.app/RJf1PnhlAQ_5oZcgHo-g3I__7nIsAqFWoCj3-4dq_Hs/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation1.webp",
                        description = "Card 1 Image"
                    ),
                    colorScheme = AppColors(
                        startGradient = "#713A65".toColorOrDefault(),
                        endGradient = "#713A65".toColorOrDefault(),
                        strokeStartColor = "#33FFFFFF".toColorOrDefault(),
                        strokeEndColor = "#CCFFFFFF".toColorOrDefault(),
                        background = "#992D7E".toColorOrDefault()
                    ),
                    icon = Image(
                        imageUrl = "", // No icon provided in JSON card object
                        description = "Card 1 Icon"
                    )
                ),
                Card(
                    expandedText = "Buy gold anytime,anywhere",
                    collapsedText = "Buy gold anytime,anywhere",
                    image = Image(
                        imageUrl = "https://img.myjar.app/yXXRNLKGxWkoWRyyzurnZr3UJNHbqVaCHiLk1VYGlDM/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation2.webp",
                        description = "Card 2 Image"
                    ),
                    colorScheme = AppColors(
                        startGradient = "#286642".toColorOrDefault(),
                        endGradient = "#286642".toColorOrDefault(),
                        strokeStartColor = "#33FFFFFF".toColorOrDefault(),
                        strokeEndColor = "#CCFFFFFF".toColorOrDefault(),
                        background = "#197A41".toColorOrDefault()
                    ),
                    icon = Image(
                        imageUrl = "",
                        description = "Card 2 Icon"
                    )
                ),
                Card(
                    expandedText = "Get extra gold using coupons",
                    collapsedText = "Get extra gold",
                    image = Image(
                        imageUrl = "https://img.myjar.app/fLqaeBj3sX4VtpjdRrj2_HxHPJ4YmqaFX9jPcm563cc/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation3.webp",
                        description = "Card 3 Image"
                    ),
                    colorScheme = AppColors(
                        startGradient = "#736C37".toColorOrDefault(),
                        endGradient = "#736C37".toColorOrDefault(),
                        strokeStartColor = "#33FFFFFF".toColorOrDefault(),
                        strokeEndColor = "#CCFFFFFF".toColorOrDefault(),
                        background = "#C39D26".toColorOrDefault()
                    ),
                    icon = Image(
                        imageUrl = "",
                        description = "Card 3 Icon"
                    )
                ),
            ),
            toolbar = Toolbar(
                icon = Image(
                    imageUrl = "https://img.myjar.app/yboPYuxcnt49zki8Iw_vKoPDH-boET8q9ZY5FIA14X4/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/BuyGoldFlow/instantSaveLightningFlashIcon.webp",
                    description = "Toolbar Icon"
                ),
                text = "INSTANT SAVING",
                actionText = "know more",
                colorScheme = AppColors(
                    text = "#FDF3D6".toColorOrDefault(),
                    strokeStartColor = "#272239".toColorOrDefault(),
                    background = "#272239".toColorOrDefault()
                    // other colors defaulted
                )
            )
        )
    }
}

@Immutable
data class Image(
    val imageUrl: String,
    val description: String,
)

@Immutable
data class Card(
    val expandedText: String,
    val collapsedText: String,
    val image: Image,
    val colorScheme: AppColors,
    val icon: Image
)

@Immutable
data class Toolbar(
    val icon: Image,
    val text: String,
    val actionText: String,
    val colorScheme: AppColors
)

