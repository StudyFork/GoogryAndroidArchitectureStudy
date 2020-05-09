package com.hwaniiidev.architecture

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCall {
    private val serverUrl: String = "https://openapi.naver.com/"

    fun setUp(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}