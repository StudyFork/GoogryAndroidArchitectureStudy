package com.aiden.aiden.architecturepatternstudy.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val apiUrl = "https://api.upbit.com"
private val mainClient =
    OkHttpClient().newBuilder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(1, TimeUnit.SECONDS).build()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(apiUrl)
    .client(mainClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

