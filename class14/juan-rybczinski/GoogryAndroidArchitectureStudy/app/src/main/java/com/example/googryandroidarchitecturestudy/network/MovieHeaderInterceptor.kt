package com.example.googryandroidarchitecturestudy.network

import com.example.googryandroidarchitecturestudy.App
import com.example.googryandroidarchitecturestudy.R
import okhttp3.Interceptor
import okhttp3.Response

class MovieHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", App.instance.getString(R.string.naver_client_id))
                .addHeader(
                    "X-Naver-Client-Secret",
                    App.instance.getString(R.string.naver_client_secret)
                )
                .build()
        )
    }
}