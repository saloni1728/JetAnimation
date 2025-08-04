package com.example.invest.data.source

import com.example.invest.domain.dtos.IDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiClient {
    @GET
    suspend fun get(
        @Url url: String,
        @QueryMap map: @JvmSuppressWildcards Map<String, String> = emptyMap(),
        @HeaderMap headerMap: Map<String, String> = emptyMap()
    ): Response<IDto>

    @POST
    @JvmSuppressWildcards
    suspend fun post(
        @Url url: String,
        @QueryMap map: @JvmSuppressWildcards Map<String, String> = emptyMap(),
        @Body body: IDto, @HeaderMap headerMap: Map<String, String> = emptyMap()
    ): Response<IDto>

    @DELETE
    suspend fun delete(
        @Url url: String,
        @QueryMap map: @JvmSuppressWildcards Map<String, String> = emptyMap(),
        @HeaderMap headerMap: Map<String, String> = emptyMap()
    ): Response<IDto>

    @PUT
    suspend fun put(
        @Url url: String,
        @QueryMap map: @JvmSuppressWildcards Map<String, String> = emptyMap(),
        @Body body: @JvmSuppressWildcards IDto,
        @HeaderMap headerMap: Map<String, String> = emptyMap()
    ): Response<IDto>
}