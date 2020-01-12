package com.example.study.data.datasource.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverApiServiceImpl {
    private val BASE_URL = "https://openapi.naver.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val naverRetrofitService = retrofit.create(NaverApiService::class.java)


}