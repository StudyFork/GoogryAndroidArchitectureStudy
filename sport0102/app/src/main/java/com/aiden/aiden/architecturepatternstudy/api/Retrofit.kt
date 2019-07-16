package com.aiden.aiden.architecturepatternstudy.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private const val API_URL = "https://api.upbit.com"

    private val mainClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(mainClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}