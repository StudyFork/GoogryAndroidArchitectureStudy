package com.tsdev.tsandroid

object NaverAPI {
    private const val baseUrl = BuildConfig.BASE_URL

    val movieAPI by lazy {
        creatorRetrofit<MovieInterface>(baseUrl)
    }
}