package com.tsdev.tsandroid

object NaverAPI {
    private const val baseUrl = BuildConfig.BASE_URL

    val movieAPI
        get() = createRetrofit(baseUrl, MovieInterface::class.java)
}