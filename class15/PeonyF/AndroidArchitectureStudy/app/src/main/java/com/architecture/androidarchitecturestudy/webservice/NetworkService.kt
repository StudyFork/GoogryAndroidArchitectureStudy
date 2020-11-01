package com.architecture.androidarchitecturestudy.webservice

import com.architecture.androidarchitecturestudy.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("v1/search/movie.json")
    fun getMovie(@Query("query") query: String): Call<MovieResponse>
}