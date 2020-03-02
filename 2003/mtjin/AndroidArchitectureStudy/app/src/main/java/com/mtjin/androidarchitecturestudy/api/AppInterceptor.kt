package com.mtjin.androidarchitecturestudy.api

import com.mtjin.androidarchitecturestudy.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder().run {
            addHeader("X-Naver-Client-Id", R.string.client_id.toString())
            addHeader("X-Naver-Client-Secret", R.string.client_secret.toString())
            build()
        }
        proceed(newRequest)
    }
}