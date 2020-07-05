package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.MovieMeta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie.json")
    fun searchMovie(
        @Query("query") title: String
    ): Call<MovieMeta>
}