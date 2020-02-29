package com.jay.remote.network.service

import com.jay.remote.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Single<ResponseNaverQuery<MovieItem>>

    @GET("v1/search/image.json")
    fun getImages(
        @Query("query") query: String
    ): Single<ResponseNaverQuery<ImageItem>>

    @GET("v1/search/blog.json")
    fun getBlog(
        @Query("query") query: String
    ): Single<ResponseNaverQuery<BlogItem>>

    @GET("v1/search/kin.json")
    fun getKin(
        @Query("query") query: String
    ): Single<ResponseNaverQuery<KinItem>>
}