package com.architecture.androidarchitecturestudy.webservice

import com.architecture.androidarchitecturestudy.util.Config
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Naver-Client-Id", Config.CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", Config.CLIENT_SECRET)
                .build()
        )
}