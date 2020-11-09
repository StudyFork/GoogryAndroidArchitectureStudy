package com.example.googryandroidarchitecturestudy.network

import com.example.googryandroidarchitecturestudy.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/v1/search/movie.json")
    suspend fun searchMovies(
        @Query("query") query: String
    ): MovieResponse

}