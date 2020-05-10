package com.example.architecture.retrofit

import com.example.architecture.BuildConfig
import com.example.architecture.data.model.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieSearchService {

    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.Naver_Client_Id}"
        , "X-Naver-Client-Secret: ${BuildConfig.Naver_Client_Secret}"
    )
    @GET("/v1/search/movie.json")
    fun requestSearchMovie(
        @Query("query") title: String
    ): Call<MovieResponseModel>


}