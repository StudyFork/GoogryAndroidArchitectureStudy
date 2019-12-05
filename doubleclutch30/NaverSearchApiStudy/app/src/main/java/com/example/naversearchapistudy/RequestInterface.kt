package com.example.naversearchapistudy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {

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
}