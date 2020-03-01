package com.example.study.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApplicationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", "AZeVMtYlsaS7bdr8W7PX")
            .addHeader("X-Naver-Client-Secret", "a7hDdCsKST")

        val response = chain.proceed(request.build())

        return response
    }
}