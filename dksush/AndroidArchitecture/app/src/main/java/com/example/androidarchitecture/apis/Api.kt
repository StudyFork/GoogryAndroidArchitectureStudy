package com.example.androidarchitecture.apis

import com.example.androidarchitecture.models.BlogData
import com.example.androidarchitecture.models.ImageData
import com.example.androidarchitecture.models.KinData
import com.example.androidarchitecture.models.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        val base_url: String = "https://openapi.naver.com/v1/search/"
    }


    @GET("movie.json")
    fun getMovielist(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<MovieData>

    @GET("image")
    fun getImagelist(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<ImageData>


    @GET("blog.json")
    fun getBloglist(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<BlogData>


    @GET("kin.json")
    fun getKinlist(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<KinData>


}