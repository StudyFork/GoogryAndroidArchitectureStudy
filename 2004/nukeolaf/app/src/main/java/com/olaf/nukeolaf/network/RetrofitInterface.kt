package com.olaf.nukeolaf.network

import com.olaf.nukeolaf.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/v1/search/movie.json")
    fun searchMovie(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<MovieResponse>

}