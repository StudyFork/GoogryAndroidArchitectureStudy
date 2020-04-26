package com.hwaniiidev.architecture

import com.hwaniiidev.architecture.Model.ResponseMovieSearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/search/movie.json")
    fun getMovieSearchData(
        @Query("query") query: String
    ): retrofit2.Call<ResponseMovieSearchData>
}