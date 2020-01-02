package com.example.architecture_project.data.datasource.remote.retrofit

import com.example.architecture_project.data.model.NaverApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {

    @GET("/v1/search/movie.json")
    fun getMovie(
        @Query("query") keyword: String
    ): Call<NaverApi>
}