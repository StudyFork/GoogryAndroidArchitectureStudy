package com.example.architecturestudy.network

import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v1/search/movie.json")
    fun requestMovies(
        @Query("query") query:String
    ): Single<MovieData>

    @GET("v1/search/blog.json")
    fun requestBlog(
        @Query("query") query: String
    ): Single<BlogData>

    @GET("v1/search/kin.json")
    fun requestKin(
        @Query("query") query: String
    ): Single<KinData>

    @GET("v1/search/image.json")
    fun requestImage(
        @Query("query") query: String
    ): Single<ImageData>
}