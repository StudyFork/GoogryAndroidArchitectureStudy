package com.wybh.androidarchitecturestudy.retrofit

import com.wybh.androidarchitecturestudy.App
import com.wybh.androidarchitecturestudy.R
import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", App.instance.getString(R.string.naver_client_key))
            .addHeader("X-Naver-Client-Secret", App.instance.getString(R.string.naver_client_secret_key))
            .build()

        return chain.proceed(newRequest)
    }

}