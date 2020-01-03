package com.song2.myapplication.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl {
    private const val BASE_URL = "https://openapi.naver.com"

    val okhttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(CookiesInterceptor())
        .addNetworkInterceptor(CookiesInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NetworkService = retrofit.create(NetworkService::class.java)
}