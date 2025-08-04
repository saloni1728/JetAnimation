package com.example.invest.data.network

import com.example.invest.domain.dtos.IDto
import com.example.invest.domain.dtos.OnboardingResponseDto
import com.example.invest.domain.model.Failure
import com.example.invest.domain.model.REST
import com.example.invest.domain.model.Result
import com.example.invest.utils.CommonUtils
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

class OnboardingRemoteSource @Inject constructor(
    private val remoteSource: RemoteSource
) {
    suspend fun getOnboardingData(): Result<Failure, OnboardingResponseDto> = remoteSource.networkCall(
        dto = OnboardingResponseDto::class.java,
        request = REST.GET(
            url = CommonUtils.getUrl(path = "6bd7fe93af056b66e19c"),
            extraHeaders = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json"
            )
        )
    )
}