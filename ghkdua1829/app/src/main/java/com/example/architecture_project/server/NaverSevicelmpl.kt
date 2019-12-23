package com.example.architecture_project.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverSevicelmpl {
    private const val BASE_URL = "https://openapi.naver.com"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NaverService = retrofit.create(NaverService::class.java)
}