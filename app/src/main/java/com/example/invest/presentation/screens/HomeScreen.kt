package com.example.invest.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.invest.R
import com.example.invest.domain.model.Screen
import com.example.invest.presentation.Components.EmptyState
import com.example.invest.presentation.Components.Toolbar
import com.example.invest.ui.theme.AppColors

@Composable
fun HomeScreen(navigator: NavController, innerPadding: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.default.background)
        .padding(innerPadding)
    ) {
        Toolbar(
            toolbarText = Screen.Home.name,
            isVisible = true,
            onClick = {
                navigator.navigateUp()
            }
        )
        EmptyState(
            introTitle = stringResource(R.string.welcome),
            introSubtitle = stringResource(R.string.home_page),
        )
    }
}