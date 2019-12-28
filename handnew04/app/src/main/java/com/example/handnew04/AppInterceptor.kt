package com.example.handnew04

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("X-Naver-Client-Id", "_dxLf04IXVNiUhZX4W5y")
            .addHeader("X-Naver-Client-Secret", "9NQwQGbGve")
            .build()
        return chain.proceed(newRequest)
    }
}