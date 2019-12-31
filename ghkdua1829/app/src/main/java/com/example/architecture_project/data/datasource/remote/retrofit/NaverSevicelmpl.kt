package com.example.architecture_project.data.datasource.remote.retrofit

import com.example.architecture_project.intercepter.CookiesIntercepter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverSevicelmpl {
    private const val BASE_URL = "https://openapi.naver.com"

    val okhttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(CookiesIntercepter())
        .addNetworkInterceptor(CookiesIntercepter()).build()
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(
        okhttpClient
    )
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service: NaverService = retrofit.create(
        NaverService::class.java
    )
}