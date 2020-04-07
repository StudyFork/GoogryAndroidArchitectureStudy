package com.example.studyforkandroid.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    private const val baseUrl: String = "https://openapi.naver.com/v1/"

    val instance: RetrofitAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitAPI::class.java)
    }
}