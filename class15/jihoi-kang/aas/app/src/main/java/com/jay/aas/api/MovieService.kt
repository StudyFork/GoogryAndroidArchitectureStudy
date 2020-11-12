package com.jay.aas.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/v1/search/movie.json")
    suspend fun getMovies(
        @Query("query") query: String,
    ): MovieResponse

}
