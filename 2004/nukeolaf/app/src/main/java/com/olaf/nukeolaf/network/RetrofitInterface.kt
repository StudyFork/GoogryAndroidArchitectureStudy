package com.olaf.nukeolaf.network

import com.olaf.nukeolaf.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/v1/search/movie.json")
    fun searchMovie(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1
    ): Call<MovieResponse>

}