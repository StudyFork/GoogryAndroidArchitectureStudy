package com.example.architecturestudy.network

import com.example.architecturestudy.data.MovieMeta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie.json")
    fun searchMovie(
        @Query("query") title: String
    ): Call<MovieMeta>
}