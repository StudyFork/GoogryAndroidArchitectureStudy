package com.olaf.nukeolaf

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("movie.json")
    fun searchMovie(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<Movie>

}