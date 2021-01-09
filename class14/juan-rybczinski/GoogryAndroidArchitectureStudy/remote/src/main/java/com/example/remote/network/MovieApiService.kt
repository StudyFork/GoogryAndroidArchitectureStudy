package com.example.remote.network

import com.example.data.model.network.NetworkMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/v1/search/movie.json")
    suspend fun searchMovies(
        @Query("query") query: String
    ): NetworkMovie

}