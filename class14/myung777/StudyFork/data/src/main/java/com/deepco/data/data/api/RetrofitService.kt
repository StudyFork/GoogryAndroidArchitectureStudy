package com.deepco.data.data.api

import com.deepco.data.data.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<MovieData>
}