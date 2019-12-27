package com.onit.googlearchitecturestudy

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    val naverApiService: NaverApiService = Retrofit.Builder().apply {
        baseUrl(Config.NAVER_API_BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        client(OkHttpClient.Builder().apply {
            interceptors().add(NaverApiInterceptor())
        }.build())
    }.build().create(NaverApiService::class.java)
}