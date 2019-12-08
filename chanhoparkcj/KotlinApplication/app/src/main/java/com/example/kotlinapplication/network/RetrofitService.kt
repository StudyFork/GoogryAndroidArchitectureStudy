package com.example.kotlinapplication.network

import com.example.kotlinapplication.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/search/movie.json")
    fun getMovieCall(@Query("query") query: String): Single<ResponseItems<MovieItems>>

    @GET("v1/search/image")
    fun getImageCall(@Query("query") query: String): Single<ResponseItems<ImageItems>>

    @GET("v1/search/blog.json")
    fun getBlogCall(@Query("query") query: String): Single<ResponseItems<BlogItems>>

    @GET("v1/search/kin.json")
    fun getKinCall(@Query("query") query: String): Single<ResponseItems<KinItems>>

}