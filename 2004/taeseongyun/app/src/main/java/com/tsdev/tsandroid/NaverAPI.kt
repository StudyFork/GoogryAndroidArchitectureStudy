package com.tsdev.tsandroid

object NaverAPI {
    private const val baseUrl = "https://openapi.naver.com/v1/"

    val movieAPI
        get() = createRetrofit(baseUrl, MovieInterface::class.java)
}