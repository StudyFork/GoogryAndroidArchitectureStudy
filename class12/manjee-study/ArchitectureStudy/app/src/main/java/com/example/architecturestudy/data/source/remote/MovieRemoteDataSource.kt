package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.MovieMeta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRemoteDataSource {
    @GET("movie.json")
    fun searchMovie(
        @Query("query") query: String
    ): Call<MovieMeta>
}