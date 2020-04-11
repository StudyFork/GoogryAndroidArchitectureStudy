package com.tsdev.tsandroid

import com.tsdev.tsandroid.data.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieInterface {
    @GET("search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String
    ): Single<MovieResponse>
}