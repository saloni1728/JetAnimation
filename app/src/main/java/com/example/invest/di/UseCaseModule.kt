package com.example.invest.di

import com.example.invest.domain.repository.IOnboardingRepo
import com.example.invest.domain.usecases.OnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideOnboardingUseCase(
        onboardingRepo: IOnboardingRepo
    ): OnboardingUseCase = OnboardingUseCase(onboardingRepo)
}