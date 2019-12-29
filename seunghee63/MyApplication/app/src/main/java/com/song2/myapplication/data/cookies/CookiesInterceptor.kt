package com.song2.myapplication.data.cookies

import com.song2.myapplication.util.config
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        builder.addHeader("X-Naver-Client-Id", config.clientId)
            .addHeader("X-Naver-Client-Secret", config.secret)
            .build()

        return chain.proceed(builder.build())
    }
}