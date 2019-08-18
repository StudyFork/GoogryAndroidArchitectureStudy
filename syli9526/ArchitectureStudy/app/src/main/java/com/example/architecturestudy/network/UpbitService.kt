package com.example.architecturestudy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpbitService {

    private val url = "https://api.upbit.com"
    val retrofit: ApiService

    init {

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    companion object {
        private var instance : UpbitService? = null

        fun getInstance(): UpbitService {
            return instance ?: synchronized(this) {
                instance
                    ?: UpbitService().also { instance = it }
            }
        }
    }


}