package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val BASE_URL = "https://openapi.naver.com/v1/"
    val TIMEOUT: Long = 15

    private val okHttpClient: OkHttpClient
    get()
    {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): MovieApi = retrofit.create(MovieApi::class.java)
}