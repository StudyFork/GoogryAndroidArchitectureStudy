package com.example.googryandroidarchitecturestudy.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MovieApi {

    private const val BASE_URL = "https://openapi.naver.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(MovieHeaderInterceptor())
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val movieService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}