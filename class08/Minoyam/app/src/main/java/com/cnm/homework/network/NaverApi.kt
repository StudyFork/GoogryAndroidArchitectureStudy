package com.cnm.homework.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {
    @GET("v1/search/movie.json")
    fun getNaverMovie(
        @Query("query") query: String
    ): Observable<NaverResponse>
}