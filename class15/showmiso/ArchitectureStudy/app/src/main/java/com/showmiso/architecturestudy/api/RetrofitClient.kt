package com.showmiso.architecturestudy.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun createService(url: String, clientId: String, clientSecret: String): APIInterface {
        val headerInterceptor = HeaderInterceptor(clientId, clientSecret)
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(headerInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(APIInterface::class.java)
    }

    class HeaderInterceptor(
        private val clientId: String,
        private val clientSecret: String
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build()
            return chain.proceed(request)
        }
    }

}