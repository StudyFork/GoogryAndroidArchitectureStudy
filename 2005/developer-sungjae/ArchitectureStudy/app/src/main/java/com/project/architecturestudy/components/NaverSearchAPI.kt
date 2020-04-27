package com.project.architecturestudy.components

import android.graphics.Movie
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface NaverSearchAPI {
    @GET("movie.json")
    fun getMovies(
        @Query("query") title: String?,
        @Query("display") displaySize: Int,
        @Query("start") startPosition: Int
    ): Flowable<Movie?>?

}