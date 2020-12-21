package com.example.studyfork.data

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    private val clientId = "7et7ziEIFBngKSf4WOri"
    private val clientSecret = "1w6dwTIT7D"

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build()
        )
    }
}