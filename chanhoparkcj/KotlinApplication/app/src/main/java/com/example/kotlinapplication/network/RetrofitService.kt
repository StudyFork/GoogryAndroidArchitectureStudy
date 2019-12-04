package com.example.kotlinapplication.network

import com.example.kotlinapplication.model.ResponseItems
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie.json")
    fun GET_MOVIE_CALL(@Query("query") query: String): Single<ResponseItems>
}