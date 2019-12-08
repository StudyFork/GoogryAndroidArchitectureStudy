package com.example.kotlinapplication.network

import com.example.kotlinapplication.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/search/movie.json")
    fun GET_MOVIE_CALL(@Query("query") query: String): Single<ResponseItems<MovieItems>>

    @GET("v1/search/image")
    fun GET_IMAGE_CALL(@Query("query") query: String): Single<ResponseItems<ImageItems>>

    @GET("v1/search/blog.json")
    fun GET_BLOG_CALL(@Query("query") query: String): Single<ResponseItems<BlogItems>>

    @GET("v1/search/kin.json")
    fun GET_KIN_CALL(@Query("query") query: String): Single<ResponseItems<KinItems>>

}