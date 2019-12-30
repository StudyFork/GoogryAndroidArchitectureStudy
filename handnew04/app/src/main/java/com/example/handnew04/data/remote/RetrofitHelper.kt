package com.example.handnew04.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object okHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(MovieAppInterceptor())
        .addInterceptor(initLogInterceptor())
        .build()
}

object buildRetrofitToNaver {
    private val URL = "https://openapi.naver.com"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.okHttpClient)
        .build()
}

fun retrofitService() = buildRetrofitToNaver.retrofit.create(RetrofitService::class.java)


