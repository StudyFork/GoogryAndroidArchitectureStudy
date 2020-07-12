package com.hyper.hyapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://openapi.naver.com"

object NaverRetrofit {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: NaverAPI = retrofit.create(NaverAPI::class.java)
}