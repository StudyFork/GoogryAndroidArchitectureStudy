package com.example.studyfork

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    @GET("v1/search/movie.json")
    fun getMovieList(@Query("query") query: String): Single<ResponseBody>
}