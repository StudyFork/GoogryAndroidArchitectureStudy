package com.mtjin.androidarchitecturestudy.api

import com.mtjin.androidarchitecturestudy.data.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String
    ): Single<MovieResponse>
}