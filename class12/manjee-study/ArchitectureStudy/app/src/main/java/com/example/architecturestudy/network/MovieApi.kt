package com.example.architecturestudy.network

import com.example.architecturestudy.data.MovieMeta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers(
        "Content-Type:application/json;charset=UTF-8",
        "X-Naver-Client-Id:JSnG5WlnTivnbGj7NFqL",
        "X-Naver-Client-Secret:iDSdCN3VjL"
    )
    @GET("movie.json")
    fun searchMovie(
        @Query("query") title: String
    ): Call<MovieMeta>
}