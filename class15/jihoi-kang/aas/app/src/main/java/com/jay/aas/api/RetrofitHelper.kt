package com.jay.aas.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitHelper @Inject constructor() {

    val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_NAVER_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    companion object {
        private const val BASE_NAVER_API_PATH = "https://openapi.naver.com/"
    }

}