package com.song2.myapplication.network

import com.song2.myapplication.data.cookies.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl {
    private const val BASE_URL = "https://openapi.naver.com"

    val okhttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ReceivedCookiesInterceptor())
        .addNetworkInterceptor(ReceivedCookiesInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NetworkService = retrofit.create(NetworkService::class.java)
}