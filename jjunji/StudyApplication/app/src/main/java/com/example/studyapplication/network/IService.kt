package com.example.studyapplication.network

import com.example.studyapplication.vo.BlogInfo
import com.example.studyapplication.vo.ImageInfo
import com.example.studyapplication.vo.KinInfo
import com.example.studyapplication.vo.MovieInfo
import retrofit2.Call
import retrofit2.http.GET

interface IService {
    @GET("/search/movie.json")
    fun getMovieList() : Call<MovieInfo>

    @GET("/search/image.json")
    fun getImageList() : Call<ImageInfo>

    @GET("/search/blog.json")
    fun getBlogList() : Call<BlogInfo>

    @GET("/search/kin.json")
    fun getKinList() : Call<KinInfo>
}