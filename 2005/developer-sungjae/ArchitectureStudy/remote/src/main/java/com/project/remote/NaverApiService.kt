package com.project.remote

import com.project.remote.response.NaverMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApiService {

    @GET("v1/search/movie.json")
    fun getMovies(@Query("query") title: String): Single<NaverMovieResponse>

}