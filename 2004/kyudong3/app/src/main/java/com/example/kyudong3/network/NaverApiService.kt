package com.example.kyudong3.network

import com.example.kyudong3.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {
    @GET("v1/search/movie")
    fun getSearchMovie(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") movieName: String
    ): Call<MovieList>
}