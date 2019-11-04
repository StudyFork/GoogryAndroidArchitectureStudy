package com.example.androidstudy.api

import com.example.androidstudy.api.service.ApiNaverService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://openapi.naver.com/"

object RetrofitBuilder {
    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun instance(): ApiNaverService = retrofit.create(ApiNaverService::class.java)
}

