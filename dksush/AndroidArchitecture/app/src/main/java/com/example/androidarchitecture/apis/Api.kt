package com.example.androidarchitecture.apis

import com.example.androidarchitecture.data.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL: String = "https://openapi.naver.com/"
    }


    @GET("v1/search/movie.json")
    fun getMovieList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<NaverQueryResponse<MovieData>>

    @GET("v1/search/image")
    fun getImageList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<NaverQueryResponse<ImageData>>


    @GET("v1/search/blog.json")
    fun getBlogList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<NaverQueryResponse<BlogData>>


    @GET("v1/search/kin.json")
    fun getKinList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Call<NaverQueryResponse<KinData>>


}