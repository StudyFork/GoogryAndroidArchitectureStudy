package com.jay.architecturestudy.network.service

import com.jay.architecturestudy.model.ResponseBlog
import com.jay.architecturestudy.model.ResponseImages
import com.jay.architecturestudy.model.ResponseKin
import com.jay.architecturestudy.model.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Call<ResponseMovies>

    @GET("v1/search/image.json")
    fun getImages(
        @Query("query") query: String
    ): Call<ResponseImages>

    @GET("v1/search/blog.json")
    fun getBlog(
        @Query("query") query: String
    ): Call<ResponseBlog>

    @GET("v1/search/kin.json")
    fun getKin(
        @Query("query") query: String
    ): Call<ResponseKin>
}