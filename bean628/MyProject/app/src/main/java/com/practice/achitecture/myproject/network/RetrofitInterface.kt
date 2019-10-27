package com.practice.achitecture.myproject.network

import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("v1/search/movie.json")
    fun searchMovie(
        @Query("query") query: String
    ): Call<ResultOfSearchingModel>

    @GET("v1/search/book.json")
    fun searchBook(
        @Query("query") query: String
    ): Call<ResultOfSearchingModel>

    @GET("v1/search/blog.json")
    fun searchBlog(
        @Query("query") query: String
    ): Call<ResultOfSearchingModel>

    @GET("v1/search/news.json")
    fun searchNews(
        @Query("query") query: String
    ): Call<ResultOfSearchingModel>

}