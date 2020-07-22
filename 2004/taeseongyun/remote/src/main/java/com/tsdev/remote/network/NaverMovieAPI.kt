package com.tsdev.remote.network

import com.tsdev.remote.model.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieAPI {
    @GET("search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String
    ): Single<MovieResponse>
}