package com.mtjin.androidarchitecturestudy

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Header("X-Naver-Client-Id") clientId : String,
        @Header("X-Naver-Client-Secret") clientSecret : String,
        @Query("query") query : String
    ) : Single<MovieResponse>
}