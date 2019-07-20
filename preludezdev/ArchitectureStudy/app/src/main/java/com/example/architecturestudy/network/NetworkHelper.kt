package com.example.architecturestudy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {
    const val API_URL = "https://api.upbit.com/"

    private val retrofit : Retrofit
    val coinApiService : CoinApiService

    init{
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        coinApiService = retrofit.create(CoinApiService::class.java)
    }

}