package com.example.architecturestudy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    private val retrofit: Retrofit
    val coinApiService: CoinApiService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        coinApiService = retrofit.create(CoinApiService::class.java)
    }

    companion object {
        const val API_URL = "https://api.upbit.com/"
        private var INSTANCE: RetrofitHelper? = null

        fun getInstance(): RetrofitHelper {
            return INSTANCE ?: RetrofitHelper().apply { INSTANCE = this }
        }
    }

}