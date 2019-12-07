package com.jay.architecturestudy.network.service

import com.jay.architecturestudy.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Call<ResponseNaverQuery<Movie>>

    @GET("v1/search/image.json")
    fun getImages(
        @Query("query") query: String
    ): Call<ResponseNaverQuery<Image>>

    @GET("v1/search/blog.json")
    fun getBlog(
        @Query("query") query: String
    ): Call<ResponseNaverQuery<Blog>>

    @GET("v1/search/kin.json")
    fun getKin(
        @Query("query") query: String
    ): Call<ResponseNaverQuery<Kin>>
}