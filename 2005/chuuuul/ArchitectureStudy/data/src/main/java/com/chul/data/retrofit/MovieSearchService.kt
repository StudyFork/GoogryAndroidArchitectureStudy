package com.chul.data.retrofit


import com.chul.data.BuildConfig
import com.chul.data.model.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface MovieSearchService {

    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.Naver_Client_Id}"
        , "X-Naver-Client-Secret: ${BuildConfig.Naver_Client_Secret}"
    )
    @GET("/v1/search/movie.json")
    fun requestSearchMovie(
        @Query("query") title: String
    ): Call<MovieResponseModel>


}