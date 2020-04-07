package com.example.studyforkandroid.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {


    private var baseUrl: String = "https://openapi.naver.com/v1/"

    val instance: RetrofitAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitAPI::class.java)
    }
}