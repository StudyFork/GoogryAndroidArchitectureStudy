package com.hhi.myapplication.api

import com.hhi.myapplication.data.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverAPI {
    @GET("v1/search/movie.json")
    fun searchMovies(
        @Query("query") query: String
    ): Call<MovieData.Response>
}