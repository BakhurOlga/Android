package com.olg.bakhur.domain.model.result

import com.olg.bakhur.domain.error.ApplicationError

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val error: ApplicationError) : Result<Nothing>()
}

inline fun <R, T> Result<T>.map(transform: (value: T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Error -> Result.Error(this.error)
    }
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) action(value)
    return this
}

inline fun <T> Result<T>.onError(action: (exception: ApplicationError) -> Unit): Result<T> {
    if (this is Result.Error) action(error)
    return this
}