package com.example.dkarch.domain.api.service

import com.example.dkarch.data.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieService {

    @GET("v1/search/movie.json")
    fun getMovieList(@Query("query") query: String): Single<Response<MovieResponse>>

}
