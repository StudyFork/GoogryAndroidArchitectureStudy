package com.example.myproject.retrofit

import com.example.myproject.retrofit.service.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://openapi.naver.com/v1/search"

object RetrofitClient {
    private val intercepter =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val builder = OkHttpClient.Builder()
        .addInterceptor(intercepter)
    private val mOkHttpClient = builder.build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): MovieService = retrofit.create(MovieService::class.java)
}