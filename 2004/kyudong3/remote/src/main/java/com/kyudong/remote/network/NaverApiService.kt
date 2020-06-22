package com.kyudong.remote.network

import com.kyudong.remote.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {
    @GET("v1/search/movie")
    fun getSearchMovie(
        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.CLIENT_ID,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("query") movieName: String
    ): Call<MovieReceiver>
}