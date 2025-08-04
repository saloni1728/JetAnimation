package com.example.invest.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.invest.ui.theme.AppColors

object CommonUtils {
    fun String.toColorOrDefault(default: Color = AppColors.default.primary): Color {
        val hexColorRegex = Regex("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$")
        return if (hexColorRegex.matches(this)) {
            try {
                Color(this.toColorInt())
            } catch (e: IllegalArgumentException) {
                default
            }
        } else {
            default
        }
    }

    fun getUrl(
        path: String,
        baseUrl: String = "https://api.npoint.io/",
        queryMap: Map<String, Any> = emptyMap()
    ): String {
        val queryString = if (queryMap.isNotEmpty()) {
            "?" + queryMap.entries.joinToString("&") { "${it.key}=${it.value}" }
        } else {
            ""
        }
        return "$baseUrl$path$queryString"
    }
}