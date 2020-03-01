package com.example.study.data.source.remote.network

import com.example.study.data.source.remote.network.interceptor.ApplicationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NaverApiClient {
    private val BASE_URL = "https://openapi.naver.com/"

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder() //.build() 해야 OkHttpClient가 됨
            .addInterceptor(ApplicationInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    val naverRetrofitService = retrofit.create(
        NaverApiService::class.java)


}