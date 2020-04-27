package com.project.architecturestudy.components

import com.project.architecturestudy.models.MovieData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface NaverSearchAPI {
    @GET("movie.json")
    fun getMovies(
        @Query("query") title: String?
    ): Flowable<MovieData>?

}