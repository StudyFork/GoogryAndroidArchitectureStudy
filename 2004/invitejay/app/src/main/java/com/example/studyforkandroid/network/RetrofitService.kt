package com.example.studyforkandroid.network

import com.example.studyforkandroid.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    private const val baseUrl: String = BuildConfig.BASE_URI

    val instance: RetrofitAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitAPI::class.java)
    }
}