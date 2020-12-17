package com.olg.bakhur.data.util

import com.olg.bakhur.domain.model.result.Result

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Result.Error(throwable)
    }
}
