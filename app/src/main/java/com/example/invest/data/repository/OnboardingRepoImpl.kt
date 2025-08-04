package com.example.invest.data.repository

import com.example.invest.data.mapper.OnboardingDataMapper
import com.example.invest.data.network.OnboardingRemoteSource
import com.example.invest.domain.model.Result
import com.example.invest.domain.model.Failure
import com.example.invest.domain.model.Success
import com.example.invest.domain.model.mapSuccess
import com.example.invest.domain.repository.IOnboardingRepo
import javax.inject.Inject

class OnboardingRepoImpl @Inject constructor(
    private val onboardingRemoteSource: OnboardingRemoteSource
): IOnboardingRepo {

    override suspend fun fetchOnboardingData(): Result<Failure, Success> {
        return onboardingRemoteSource.getOnboardingData().mapSuccess {
            val mapper = OnboardingDataMapper(it).get()
            System.out.println(mapper.saveButtonCta?.expandedText)
            mapper
        }
    }
}