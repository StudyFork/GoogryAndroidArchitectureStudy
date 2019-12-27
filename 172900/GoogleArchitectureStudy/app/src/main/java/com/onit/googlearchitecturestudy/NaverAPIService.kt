package com.onit.googlearchitecturestudy

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverAPIService {

    @GET("/v1/search/movie.json")
   suspend fun getMovieList(
        @Query("query") keyword: String
    ): Response<Movies>
}