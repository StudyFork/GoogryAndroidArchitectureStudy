package com.jay.aas.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/v1/search/movie.json")
    fun searchMovies(
        @Query("query") query: String,
    ): Call<MovieResponse>

}
