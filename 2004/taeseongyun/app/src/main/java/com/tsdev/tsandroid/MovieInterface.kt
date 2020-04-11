package com.tsdev.tsandroid

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String
    ): Single<MovieResponse>
}