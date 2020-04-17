package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.network.SearchMovieInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverMovieApi {
    @GET("v1/search/movie.json")
    @Headers(
        "X-Naver-Client-Id: cFYCgRzcjkYLOio3qUOT",
        "X-Naver-Client-Secret: FLB89pM53Z"
    )
    fun getMovieList(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("genre") genre: String
    ): Call<SearchMovieInfoResponse>
}