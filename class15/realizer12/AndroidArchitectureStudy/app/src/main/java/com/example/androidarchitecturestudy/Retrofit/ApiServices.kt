package com.example.androidarchitecturestudy.Retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiServices {

    @Headers("X-Naver-Client-Id: {67znsYa_iJOrjYNeChNL}", "X-Naver-Client-Secret: {V2h_LIRLgC}" )
    @GET("movie.json")
    fun getMovieSearchResult(@Query("query") movieName:String,
                             @Query("display")resultCount:Int): Call<ResponseBody>

}