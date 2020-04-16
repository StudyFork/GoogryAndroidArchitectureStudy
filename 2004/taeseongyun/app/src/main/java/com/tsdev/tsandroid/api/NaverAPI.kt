package com.tsdev.tsandroid.api

import com.tsdev.tsandroid.BuildConfig
import com.tsdev.tsandroid.network.NaverMovieInterface
import com.tsdev.tsandroid.network.creatorRetrofit

object NaverAPI {
    private const val baseUrl = BuildConfig.BASE_URL

    val movieAPI by lazy {
        creatorRetrofit<NaverMovieInterface>(baseUrl)
    }
}