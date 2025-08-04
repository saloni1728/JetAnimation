package com.example.invest.di

import com.example.invest.domain.dtos.IDto
import com.example.invest.domain.dtos.OnboardingResponseDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SerializationModule {

    @Provides
    @Singleton
    fun provideSerializersModule() = SerializersModule {
        polymorphic(IDto::class) {
            subclass(OnboardingResponseDto::class, OnboardingResponseDto.serializer())
        }
    }

    @Provides
    @Singleton
    fun provideJson(serializersModule: SerializersModule): Json = Json {
        this.serializersModule = serializersModule
        classDiscriminator = "success" // using status since there is only one api
        ignoreUnknownKeys = true
    }
}