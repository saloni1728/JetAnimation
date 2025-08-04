package com.example.invest.data.network

import com.example.invest.data.source.ApiClient
import com.example.invest.domain.dtos.IDto
import com.example.invest.domain.model.Failure
import com.example.invest.domain.model.REST
import com.example.invest.domain.model.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import retrofit2.Response
import javax.inject.Inject

class RemoteSource @Inject constructor(
    val apiClient: ApiClient,
    val json: Json
) {
    @OptIn(kotlinx.serialization.InternalSerializationApi::class)
    suspend inline fun <reified ResponseDto : IDto> networkCall(
        dto: Class<ResponseDto>,
        request: REST
    ): Result<Failure, ResponseDto> {
        try {
            val response: Response<IDto> = apiCall(request = request)
            if (response.isSuccessful) {
                val responseBody = response.body()?.let { json.encodeToString(IDto::class.serializer(), it) }?.toDto(dto) ?: return Result.Error(Failure.ApiError(NULL_RESPONSE))
                return Result.Success(responseBody)
            } else {
                return Result.Error(Failure.ApiError(response.errorBody()?.string() ?: UNKNOWN_ERROR))
            }
        } catch (e: Exception) {
            return Result.Error(Failure.NetworkConnectionError(e.message ?: UNKNOWN_ERROR))
        }
    }

    suspend inline fun apiCall(
        request: REST
    ): Response<IDto> {
        return when (request) {
            is REST.GET -> apiClient.get(request.url, request.queryMap, request.extraHeaders)
            is REST.POST -> apiClient.post(request.url, request.queryMap, request.body, request.extraHeaders)
            is REST.DELETE -> apiClient.delete(request.url, request.queryMap, request.extraHeaders)
            is REST.PUT -> apiClient.put(request.url, request.queryMap, request.body, request.extraHeaders)
        }
    }

    inline fun <reified ResponseDto: IDto> String.toDto(dto: Class<ResponseDto>): ResponseDto? {
        return json.decodeFromString<ResponseDto>(this)
    }

    companion object {
        const val NULL_RESPONSE = "Response body is null"
        const val UNKNOWN_ERROR = "Unknown error occurred"
    }
}