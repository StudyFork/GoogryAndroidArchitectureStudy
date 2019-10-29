package com.example.androidstudy.api

import com.example.androidstudy.api.service.APINaverService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://openapi.naver.com/"

object RetrofitBuilder {
    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun instance(): APINaverService = retrofit.create(APINaverService::class.java)
}

