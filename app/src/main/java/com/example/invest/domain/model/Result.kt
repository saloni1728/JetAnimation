package com.example.invest.domain.model

sealed class Result<out T, out R> {
    data class Success<out R>(val data: R) : Result<Nothing, R>()
    data class Error<out T>(val error: T) : Result<T, Nothing>()
}

suspend fun <Failure, T> Result<Failure, T>.handleResult(
    onError: suspend (Failure) -> Unit,
    onSuccess: suspend (T) -> Unit
) {
    when (this) {
        is Result.Success -> onSuccess(data)
        is Result.Error -> onError(error)
    }
}

sealed class Success {
    abstract class ViewState : Success()
}

sealed class Failure {
    data class ApiError(val error: String) : Failure()
    data class NetworkConnectionError(val error: String?) : Failure()
}

fun <R> Result<Failure, R>.mapSuccess(action: (R) -> Success.ViewState): Result<Failure, Success.ViewState> {
    return when (this) {
        is Result.Success -> Result.Success(action(this.data))
        is Result.Error -> this
    }
}