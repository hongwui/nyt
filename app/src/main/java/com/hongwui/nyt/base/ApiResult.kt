package com.hongwui.nyt.base

sealed class ApiResult<T>(
    val body: T? = null,
    val message: String? = null
) {
    class Success<T>(body: T): ApiResult<T>(body)
    class Error<T>(errorMessage: String?= null): ApiResult<T>(message = errorMessage)
}