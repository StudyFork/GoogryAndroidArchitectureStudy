package com.hansung.firstproject.network

import com.hansung.firstproject.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverApiServiceImpl {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(NaverApiService::class.java)
}