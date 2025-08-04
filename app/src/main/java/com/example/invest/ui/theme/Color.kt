package com.example.invest.ui.theme

import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

data class AppColors(
    val primary: Color = Color(0xFFFFFFFF),
    val background: Color = Color(0xFFFFFFFF),
    val onBackground: Color = Color(0xFF272239),
    val text: Color = Color(0xFFFFFFFF),
    val textSecondary: Color = Color(0xFFFFFFFF),
    val strokeStartColor: Color = Color(0xFFFFFFFF),
    val strokeEndColor: Color = Color(0xFFFFFFFF),
    val startGradient: Color = Color(0xFFFFFFFF).copy(alpha = 0.24f),
    val endGradient: Color = Color(0xFFFFFFFF),
    val ctaColor: Color = Color(0xFFFFFFFF)
) {
    companion object {
        val default = AppColors(
            primary = Color(0xFFFFFFFF),
            background = Color(0xFF272239),
            onBackground = Color(0x28085C4D),
            text = Color(0xFFFFFFFF),
            textSecondary = Color(0xFFF8DC83),
            strokeStartColor = Color(0xFFFFFFFF),
            strokeEndColor = Color(0xFFFFFFFF),
            startGradient = Color(0xFFFFFFFF),
            endGradient = Color(0xFFFFFFFF),
            ctaColor = Color(0xFFFFFFFF)
        )
    }
}