package com.example.invest.di

import com.example.invest.data.repository.OnboardingRepoImpl
import com.example.invest.domain.repository.IOnboardingRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindOnboardingRepo(onboardingRepoImpl: OnboardingRepoImpl): IOnboardingRepo
}