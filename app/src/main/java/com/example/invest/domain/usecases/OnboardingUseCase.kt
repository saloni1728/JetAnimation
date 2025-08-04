package com.example.invest.domain.usecases

import com.example.invest.domain.model.Failure
import com.example.invest.domain.model.Result
import com.example.invest.domain.model.Success
import com.example.invest.domain.repository.IOnboardingRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingUseCase @Inject constructor(
    private val onboardingRepo: IOnboardingRepo
) {
    suspend operator fun invoke(onStart: () -> Unit): Result<Failure, Success> {
        return withContext(Dispatchers.IO) {
            onStart.invoke()
            onboardingRepo.fetchOnboardingData()
        }
    }
}