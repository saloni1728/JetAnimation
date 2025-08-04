package com.example.invest.domain.dtos

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
@SerialName("true")
data class OnboardingResponseDto(
    @SerialName("data")
    val data: ManualBuyEducationDataWrapper? = null,
): IDto

@Serializable
data class ManualBuyEducationDataWrapper(
    @SerialName("manualBuyEducationData") val manualBuyEducationData: ManualBuyEducationData? = null
)

@Serializable
data class ManualBuyEducationData(
    @SerialName("cohort") val cohort: String? = null,
    @SerialName("ctaLottie") val ctaLottie: String? = null,
    @SerialName("seenCount") val seenCount: Int? = null,
    @SerialName("actionText") val actionText: String? = null,
    @SerialName("introTitle") val introTitle: String? = null,
    @SerialName("screenType") val screenType: String? = null,
    @SerialName("combination") val combination: String? = null,
    @SerialName("toolBarIcon") val toolBarIcon: String? = null,
    @SerialName("toolBarText") val toolBarText: String? = null,
    @SerialName("introSubtitle") val introSubtitle: String? = null,
    @SerialName("saveButtonCta") val saveButtonCta: SaveButtonCta? = null,
    @SerialName("educationCardList") val educationCardList: List<EducationCard>? = null,
    @SerialName("introSubtitleIcon") val introSubtitleIcon: String? = null,
    @SerialName("expandCardStayInterval") val expandCardStayInterval: Long? = 3000L,
    @SerialName("shouldShowOnLandingPage") val shouldShowOnLandingPage: Boolean? = false,
    @SerialName("collapseCardTiltInterval") val collapseCardTiltInterval: Long? = 1000L,
    @SerialName("shouldShowBeforeNavigating") val shouldShowBeforeNavigating: Boolean? = true,
    @SerialName("collapseExpandIntroInterval") val collapseExpandIntroInterval: Long? = 500L,
    @SerialName("bottomToCenterTranslationInterval") val bottomToCenterTranslationInterval: Long? = 1500L
)

@Serializable
data class SaveButtonCta(
    @SerialName("icon") val icon: String? = null,
    @SerialName("text") val text: String? = "",
    @SerialName("order") val order: Int? = null,
    @SerialName("deeplink") val deeplink: String? = null,
    @SerialName("textColor") val textColorHex: String? = "#000000",
    @SerialName("strokeColor") val strokeColorHex: String? = "#000000",
    @SerialName("backgroundColor") val backgroundColorHex: String? = "#FFFFFF"
)

@Serializable
data class EducationCard(
    @SerialName("image") val image: String? = "",
    @SerialName("endGradient") val endGradient: String? = "#000000",
    @SerialName("startGradient") val startGradient: String? = "#000000",
    @SerialName("strokeEndColor") val strokeEndColor: String? = "#000000",
    @SerialName("backGroundColor") val backGroundColor: String? = "#000000",
    @SerialName("expandStateText") val expandStateText: String? = "",
    @SerialName("strokeStartColor") val strokeStartColor: String? = "#000000",
    @SerialName("collapsedStateText") val collapsedStateText: String? = ""
)