package com.example.kyudong3.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                LoggingInterceptor.Builder().apply {
                    setLevel(Level.BASIC)
                    log(Platform.INFO)
                    request("Request")
                    response("Response")
                }.build()
            ).build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val naverService: NaverApiService by lazy {
        retrofit.create(NaverApiService::class.java)
    }
}