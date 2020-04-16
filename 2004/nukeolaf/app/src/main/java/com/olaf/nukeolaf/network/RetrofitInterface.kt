package com.olaf.nukeolaf.network

import com.olaf.nukeolaf.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitInterface {

    @GET("/v1/search/movie.json")
    fun searchMovie(
        @QueryMap options: Map<String, @JvmSuppressWildcards Any>
    ): Call<MovieResponse>

}