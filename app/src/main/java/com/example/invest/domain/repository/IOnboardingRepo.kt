package com.example.invest.domain.repository

import com.example.invest.domain.model.Failure
import com.example.invest.domain.model.Result
import com.example.invest.domain.model.Success

interface IOnboardingRepo {
    suspend fun fetchOnboardingData(): Result<Failure, Success>
}