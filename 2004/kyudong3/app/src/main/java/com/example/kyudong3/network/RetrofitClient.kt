package com.example.kyudong3.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val okHttpClient: OkHttpClient

    init {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(
            LoggingInterceptor.Builder().apply {
                setLevel(Level.BASIC)
                log(Platform.INFO)
                request("Request")
                response("Response")
            }.build()
        )
        okHttpClient = okHttpBuilder.build()
    }

    fun getNaverService(): NaverApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(NaverApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
    }
}