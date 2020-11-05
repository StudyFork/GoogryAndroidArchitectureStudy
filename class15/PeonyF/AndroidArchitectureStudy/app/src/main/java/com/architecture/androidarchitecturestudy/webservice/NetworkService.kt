package com.architecture.androidarchitecturestudy.webservice

import com.architecture.androidarchitecturestudy.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("v1/search/movie.json")
    fun getMovieSearch(
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<MovieResponse>
}