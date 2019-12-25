package com.song2.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object 는 싱글톤 객체!
object NetworkServiceImpl {
    private const val BASE_URL = "https://openapi.naver.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // 인터넷 통신으로 받아온 정보를 Gson 을 통해서 시리얼라이즈 하겠다.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NetworkService = retrofit.create(NetworkService::class.java)
}