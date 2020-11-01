package com.example.studyfork.api

import com.example.studyfork.model.MovieSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    @GET("v1/search/movie.json")
    fun searchMovie(@Query("query") query: String): Single<MovieSearchResponse>
}