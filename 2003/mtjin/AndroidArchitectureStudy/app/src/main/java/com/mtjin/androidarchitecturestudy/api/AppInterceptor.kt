package com.mtjin.androidarchitecturestudy.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("X-Naver-Client-Id", "33chRuAiqlSn5hn8tIme")
            .addHeader("X-Naver-Client-Secret", "fyfwt9PCUN")
            .build()

        proceed(newRequest)
    }
}