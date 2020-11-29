package com.example.googryandroidarchitecturestudy.network

import com.example.googryandroidarchitecturestudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MovieHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                .build()
        )
    }
}