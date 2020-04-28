package com.sangjin.newproject

import com.sangjin.newproject.adapter.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers(
        "X-Naver-Client-Id: Eoo188aalN5O119lfhGY",
        "X-Naver-Client-Secret: siIgsqKRng"
    )
    @GET("/v1/search/movie.xml")
    fun requestMovieList(
        @Query("query") keyword: String
    ): Call<Movie>

}