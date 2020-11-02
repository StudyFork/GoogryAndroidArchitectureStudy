package com.architecture.androidarchitecturestudy.webservice

import com.architecture.androidarchitecturestudy.util.config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", config.clientId)
                .addHeader("X-Naver-Client-Secret", config.clientSecret)
                .build()
            it.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val NETWORK_SERVICE: NetworkService = retrofit.create(NetworkService::class.java)
}