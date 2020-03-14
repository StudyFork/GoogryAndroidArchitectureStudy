package com.byiryu.study.model

import com.byiryu.study.model.data.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {
    @GET("v1/search/movie.json")
    fun getSearchMovie(@Query("query") query: String): Single<MovieResponse>
}
