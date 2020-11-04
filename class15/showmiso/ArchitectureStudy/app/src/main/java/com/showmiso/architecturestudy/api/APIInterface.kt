package com.showmiso.architecturestudy.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Single<MovieModel.MovieResponse>
}
