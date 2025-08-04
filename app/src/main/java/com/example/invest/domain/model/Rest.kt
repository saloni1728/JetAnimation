package com.example.invest.domain.model

import androidx.annotation.NonNull
import com.example.invest.domain.dtos.IDto
import retrofit2.http.PartMap
import java.io.File

sealed interface REST {
    data class GET(val url: String, val queryMap: Map<String, String> = emptyMap(), val extraHeaders: Map<String, String> = emptyMap()) : REST

    data class POST(val url: String, val queryMap: Map<String, String> = emptyMap(), @NonNull val body: IDto, val extraHeaders: Map<String, String> = emptyMap()) : REST

    data class DELETE(val url: String, val queryMap: Map<String, String> = emptyMap(), val extraHeaders: Map<String, String> = emptyMap()) : REST

    data class PUT(val url: String, val queryMap: Map<String, String> = emptyMap(), val body: IDto, val extraHeaders: Map<String, String> = emptyMap()) : REST
}