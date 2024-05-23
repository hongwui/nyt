package com.hongwui.nyt.domain

import com.hongwui.nyt.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class NytAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter("api-key", BuildConfig.NYT_APP_ID)
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}