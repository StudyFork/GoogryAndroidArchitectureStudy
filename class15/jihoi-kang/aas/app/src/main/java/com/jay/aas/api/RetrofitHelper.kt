package com.jay.aas.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_NAVER_API_PATH = "https://openapi.naver.com/"

    fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_NAVER_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}