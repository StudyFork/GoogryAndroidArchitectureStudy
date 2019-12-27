package com.onit.googlearchitecturestudy

import okhttp3.Interceptor
import okhttp3.Response

class NaverApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("X-Naver-Client-Id", Config.NAVER_API_CLIENT_ID)
            .header("X-Naver-Client-Secret", Config.NAVER_API_CLIENT_SECRET)
            .build()

        return chain.proceed(request)
    }
}