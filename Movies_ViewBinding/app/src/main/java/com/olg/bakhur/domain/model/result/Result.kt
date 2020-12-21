package com.olg.bakhur.domain.model.result

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) action(value)
    return this
}

inline fun <T> Result<T>.onError(action: (throwable: Throwable) -> Unit): Result<T> {
    if (this is Result.Error) action(throwable)
    return this
}


