package com.example.study.data.remote

import com.example.study.data.api.NaverApi
import com.example.study.view.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteService {
    val movieApiService: NaverApi
        get() = SERVICE

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val SERVICE: NaverApi = retrofit.create(
        NaverApi::class.java
    )
}