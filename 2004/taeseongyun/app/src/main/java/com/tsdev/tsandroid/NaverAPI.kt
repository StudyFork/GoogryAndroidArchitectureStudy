package com.tsdev.tsandroid

import com.tsdev.tsandroid.network.creatorRetrofit

object NaverAPI {
    private const val baseUrl = BuildConfig.BASE_URL

    val movieAPI by lazy {
        creatorRetrofit<NaverMovieInterface>(baseUrl)
    }
}