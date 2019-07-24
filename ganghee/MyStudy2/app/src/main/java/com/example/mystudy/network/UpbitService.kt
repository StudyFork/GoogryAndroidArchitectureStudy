package com.example.mystudy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpbitService {
    private val baseURL = "https://api.upbit.com/v1/"
    var upbitApi: UpbitApi

    companion object {
        private var INSTANCE: UpbitService? = null
        fun getInstance(): UpbitService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: UpbitService().also { INSTANCE = it }
            }
        }
    }

    init {
        upbitApi = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UpbitApi::class.java)
    }

}
