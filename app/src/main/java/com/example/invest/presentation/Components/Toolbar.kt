package com.example.invest.presentation.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.invest.R
import com.example.invest.presentation.custom.customClickable
import com.example.invest.ui.theme.AppColors
import com.example.invest.ui.theme.Typography

@Composable
fun Toolbar(
    toolbarText: String,
    isVisible: Boolean,
    onClick: () -> Unit,
) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.Companion
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.Companion.fillMaxSize()) {
            Spacer(modifier = Modifier.Companion.height(44.dp))

            Row(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.Companion.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_back_button),
                    contentDescription = "Back Button",
                    modifier = Modifier.Companion
                        .size(20.dp)
                        .customClickable(
                            onClick = onClick,
                            debounceTime = 500,
                            isDebounceEnable = true
                        )
                )
                Text(
                    text = toolbarText,
                    style = Typography.headlineLarge,
                    color = AppColors.Companion.default.text,
                    maxLines = 1,
                    overflow = TextOverflow.Companion.Ellipsis
                )
            }
        }
    }
}