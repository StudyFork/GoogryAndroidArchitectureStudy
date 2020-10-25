package com.hong.data.network

import com.hong.data.model.MovieResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieApi {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Call<MovieResultData>

}