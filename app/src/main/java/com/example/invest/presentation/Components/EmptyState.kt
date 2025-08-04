package com.example.invest.presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.invest.presentation.custom.RemoteImage
import com.example.invest.ui.theme.AppColors
import com.example.invest.ui.theme.Typography

@Composable
fun EmptyState(
    introTitle: String,
    introSubtitle: String,
    introSubtitleDescription: String? = null,
    introSubtitleIcon: String? = null,
) {
    Column(
        modifier = Modifier.Companion.fillMaxSize(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = introTitle,
            style = Typography.titleLarge,
            color = AppColors.Companion.default.text,
            maxLines = 1,
            overflow = TextOverflow.Companion.Ellipsis
        )
        Row(
            modifier = Modifier.Companion.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = introSubtitle,
                style = Typography.titleMedium,
                color = AppColors.Companion.default.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Companion.Ellipsis
            )
            Spacer(modifier = Modifier.Companion.width(4.dp))
            if (introSubtitleIcon != null) {
                RemoteImage(
                    url = introSubtitleIcon ?: "",
                    modifier = Modifier.Companion.height(24.dp).width(16.dp),
                    contentDescription = introSubtitleDescription
                )
            }
        }
    }
}