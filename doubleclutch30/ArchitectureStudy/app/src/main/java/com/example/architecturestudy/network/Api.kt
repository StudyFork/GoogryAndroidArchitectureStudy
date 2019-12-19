package com.example.architecturestudy.network

import com.example.architecturestudy.model.BlogData
import com.example.architecturestudy.model.ImageData
import com.example.architecturestudy.model.KinData
import com.example.architecturestudy.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v1/search/movie.json")
    fun requestMovies(
        @Query("query") query:String
    ): Call<MovieData>

    @GET("v1/search/blog.json")
    fun requestBlog(
        @Query("query") query: String
    ): Call<BlogData>

    @GET("v1/search/kin.json")
    fun requestKin(
        @Query("query") query: String
    ): Call<KinData>

    @GET("v1/search/image.json")
    fun requestImage(
        @Query("query") query: String
    ): Call<ImageData>
}