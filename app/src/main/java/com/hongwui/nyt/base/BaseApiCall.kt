package com.hongwui.nyt.base

import retrofit2.Response

abstract class BaseApiCall {
    suspend fun<T> makeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> = baseApiCall(apiCall)
}

private suspend fun <T>baseApiCall(apiCall: suspend ()-> Response<T>): ApiResult<T> {
    val response = apiCall()
    val body = response.body()
    body?.let {
        if (response.code() == 200) {
            return ApiResult.Success(body)
        } else {
            return ApiResult.Error("Api call not success")
        }
    } ?: return ApiResult.Error("Invalid Response")
}