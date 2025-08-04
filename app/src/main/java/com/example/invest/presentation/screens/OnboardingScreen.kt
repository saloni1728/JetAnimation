package com.example.invest.presentation.screens

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.invest.R
import com.example.invest.domain.model.Card
import com.example.invest.domain.model.Screen
import com.example.invest.presentation.Components.EmptyState
import com.example.invest.presentation.Components.Toolbar
import com.example.invest.presentation.custom.OneShotEffect
import com.example.invest.presentation.custom.RemoteImage
import com.example.invest.presentation.custom.customClickable
import com.example.invest.presentation.events.OnboardingEvents
import com.example.invest.presentation.viewModels.OnboardingViewModel
import com.example.invest.ui.theme.AppColors
import com.example.invest.ui.theme.Typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    innerPadding: PaddingValues,
    navigator: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val coroutineScope = rememberCoroutineScope()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    var enableClick by rememberSaveable { mutableStateOf(false) }
    val backGroundColor = remember { androidx.compose.animation.Animatable(AppColors.default.background) }

    OneShotEffect {
        viewModel.handleEvent(OnboardingEvents.SetScreenHeight(screenHeight))
        viewModel.handleEvent(OnboardingEvents.FetchOnboardingData)
    }

    LaunchedEffect(currentIndex) {
        backGroundColor.animateTo(
            targetValue = screenState.onboardingData.cards.getOrNull(currentIndex)?.colorScheme?.background ?: AppColors.default.background,
            animationSpec = tween(durationMillis = 500, easing = EaseInOut)
        )
    }

    LaunchedEffect(Unit) {
        while (!enableClick) {
            if (currentIndex == 0) {
                delay(1000)
            }
            viewModel.handleEvent(OnboardingEvents.UpdateCardList(index = currentIndex))
            delay(screenState.onboardingData.let {
                it.bottomToCenterTranslationInterval.plus(it.collapsedCardTiltTime).plus(it.expandedCardTime)
            })
            viewModel.handleEvent(OnboardingEvents.UpdateCardCollapseState(
                index = currentIndex,
                isCollapsed = true
            ))
            viewModel.handleEvent(OnboardingEvents.SetScreenHeight(screenState.screenHeightForAnimation - 86.dp))
            delay(screenState.onboardingData.collapseExpandIntroInterval)
            if (currentIndex < screenState.onboardingData.cards.size - 1) {
                currentIndex += 1
            } else {
                enableClick = true
                break
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                listOf(
                    backGroundColor.value,
                    backGroundColor.value,
                    backGroundColor.value.copy(0.4f)
                )
            )
        )
        .padding(innerPadding)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item("TopBar") {
                Toolbar(
                    toolbarText = screenState.onboardingData.toolbar?.text
                        ?: stringResource(R.string.onboarding),
                    isVisible = !screenState.cardList.isEmpty(),
                    onClick = { (navigator.context as? Activity)?.finish() }
                )

                if (screenState.cardList.isEmpty()) {
                    Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                        EmptyState(
                            introTitle = screenState.onboardingData.introTitle,
                            introSubtitle = screenState.onboardingData.introSubtitle,
                            introSubtitleIcon = screenState.onboardingData.introSubtitleIcon?.imageUrl,
                            introSubtitleDescription = screenState.onboardingData.introSubtitleIcon?.description
                        )
                    }
                }
            }

            itemsIndexed(screenState.cardList, key = { index, item -> "$index-${item.collapsedText}"}) { index, item ->
                CardsComposable(
                    bottomToCenterTranslation = screenState.onboardingData.bottomToCenterTranslationInterval.toInt(),
                    collapseCardTiltDuration = screenState.onboardingData.collapsedCardTiltTime.toInt(),
                    lottieUrl = screenState.onboardingData.downLottie,
                    screenHeight = screenState.screenHeightForAnimation,
                    showAnimations = !enableClick,
                    index = index,
                    card = item,
                    isCollapsed = screenState.cardCollapsedList[index],
                    onCardClick = { clickedIndex ->
                        if (enableClick) {
                            screenState.cardList.forEachIndexed { index, _ ->
                                viewModel.handleEvent(
                                    OnboardingEvents.UpdateCardCollapseState(
                                        index = index,
                                        isCollapsed = if (index == clickedIndex) {
                                            !screenState.cardCollapsedList[index]
                                        } else true
                                    ))
                            }
                            currentIndex = index
                            coroutineScope.launch {
                                backGroundColor.animateTo(
                                    targetValue = item.colorScheme.background,
                                    animationSpec = tween(durationMillis = 500, easing = EaseInOut)
                                )
                            }
                            if (clickedIndex < screenState.cardList.size - 1) {
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(clickedIndex + 1)
                                }
                            }
                        }
                    }
                )
            }
        }
        AnimatedVisibility(
            visible = enableClick,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            FloatingCta(
                item = screenState.onboardingData.saveButtonCta,
                onClick = {
                    navigator.navigate(Screen.Home.name)
                }
            )
        }
    }

}

@Composable
fun FloatingCta(
    item: Card?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .background(
                    color = item?.colorScheme?.background ?: AppColors.default.background,
                    shape = RoundedCornerShape(31.dp)
                )
                .border(
                    width = 1.dp,
                    color = item?.colorScheme?.strokeStartColor
                        ?: AppColors.default.strokeStartColor,
                    shape = RoundedCornerShape(31.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item?.collapsedText ?: stringResource(com.example.invest.R.string.save_gold),
                style = Typography.labelSmall,
                color = item?.colorScheme?.text ?: AppColors.default.textSecondary,
                modifier = Modifier
                    .padding(horizontal = 36.dp, vertical = 12.dp)
                    .customClickable(
                        onClick = onClick,
                        debounceTime = 1000,
                        isDebounceEnable = true
                    ),
            )
        }
    }
}

@Composable
fun CardsComposable(
    bottomToCenterTranslation: Int,
    collapseCardTiltDuration: Int,
    lottieUrl: String,
    screenHeight: Dp,
    showAnimations: Boolean,
    index: Int,
    card: Card,
    isCollapsed: Boolean,
    onCardClick: (Int) -> Unit
) {
    val cardTranslationYOffset = remember { Animatable(screenHeight.value, Spring.StiffnessLow) }
    val cardExpandedTiltState = remember { Animatable(0.5f * (if (index % 2 != 0) 1 else -1), Spring.StiffnessLow) }
    val cardCollapsedTiltState = remember { Animatable(0.5f * (if (index % 2 != 0) 1 else -1), Spring.StiffnessLow) }
    val heightState by animateDpAsState(
        targetValue = if (isCollapsed) 68.dp else 444.dp,
        animationSpec = tween(durationMillis = 500, easing = EaseInOut)
    )

    LaunchedEffect(isCollapsed) {
        if (showAnimations) {
            if (!isCollapsed) {
                cardTranslationYOffset.animateTo(
                    targetValue = if (index == 0) screenHeight.value * 0.15f else 5f,
                    animationSpec = tween(durationMillis = bottomToCenterTranslation)
                )
            } else {
                cardTranslationYOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = bottomToCenterTranslation)
                )
            }

            if (!isCollapsed) {
                cardExpandedTiltState.animateTo(
                    targetValue = 0f,
                    animationSpec = tween (
                        durationMillis = collapseCardTiltDuration,
                        easing = EaseInOut
                    )
                )
            } else {
                cardCollapsedTiltState.animateTo(
                    targetValue = 0f,
                    animationSpec = tween (
                        durationMillis = collapseCardTiltDuration,
                        easing = EaseInOut
                    )
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .then(
                if (showAnimations) {
                    Modifier
                        .offset(y = cardTranslationYOffset.value.dp)
                        .graphicsLayer {
                            if (isCollapsed) rotationZ = cardCollapsedTiltState.value * 20f
                            else rotationY = 8f * cardExpandedTiltState.value
                        }
                } else Modifier
            )
            .height(heightState)
            .background(color = Color.Black.copy(0.2f), shape = RoundedCornerShape(28.dp))
            .border(
                border = if (!isCollapsed) {
                    BorderStroke(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                card.colorScheme.strokeStartColor,
                                card.colorScheme.strokeEndColor
                            )
                        )
                    )
                } else {
                    BorderStroke(
                        width = 1.dp,
                        color = card.colorScheme.strokeStartColor
                    )
                }, shape = RoundedCornerShape(28.dp)
            )
    ) {
        if (isCollapsed) {
            CollapsedCard(
                lottieUrl = lottieUrl,
                card = card,
                onCardClick = { onCardClick(index) }
            )
        } else {
            ExpandedCard(
                card = card,
                onCardClick = { onCardClick(index) }
            )
        }
    }
}

@Composable
fun CollapsedCard(
    lottieUrl: String,
    card: Card,
    onCardClick: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Url(lottieUrl))

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .customClickable(
                onClick = {
                    onCardClick()
                },
                debounceTime = 1000,
                isDebounceEnable = true
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RemoteImage(
            url = card.image.imageUrl,
            contentDescription = card.image.description,
            modifier = Modifier
                .weight(0.1f)
                .width(32.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(16.dp)),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = card.collapsedText,
            style = Typography.labelSmall,
            color = AppColors.default.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(0.7f)
        )

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true,
            modifier = Modifier.weight(0.1f)
        )
    }
}


@Composable
fun ExpandedCard(
    card: Card,
    onCardClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .customClickable(
                onClick = {
                    onCardClick()
                },
                debounceTime = 1000,
                isDebounceEnable = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        RemoteImage(
            url = card.image.imageUrl,
            contentDescription = card.image.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .clip(RoundedCornerShape(16.dp)),
        )

        Text(
            text = card.collapsedText,
            style = Typography.titleLarge,
            color = AppColors.default.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

