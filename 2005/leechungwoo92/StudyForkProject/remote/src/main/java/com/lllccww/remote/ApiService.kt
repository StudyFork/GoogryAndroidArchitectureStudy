package com.lllccww.remote


import com.lllccww.remote.Constants.CLIENT_ID
import com.lllccww.remote.Constants.CLIENT_SECRET
import com.lllccww.remote.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers(
        "X-Naver-Client-Id:$CLIENT_ID",
        "X-Naver-Client-Secret:$CLIENT_SECRET"
    )
    @GET("v1/search/movie.json")
    fun listMovie(
        @Query("query") keyword: String

    ): Call<MovieResponse>


}