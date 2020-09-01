package com.camai.archtecherstudy.data.network

import com.camai.archtecherstudy.data.model.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("v1/search/movie.json")
    fun getMovieSearch(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<MovieResponseModel>

}