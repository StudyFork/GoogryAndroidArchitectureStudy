package com.byiryu.study.api

import com.byiryu.study.api.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {
    @GET("v1/search/movie.json")
    fun getSearchMovie(@Query("query") query: String): Single<MovieResponse>
}
