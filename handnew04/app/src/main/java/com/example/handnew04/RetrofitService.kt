package com.example.handnew04

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers("X-Naver-Client-Id: _dxLf04IXVNiUhZX4W5y", "X-Naver-Client-Secret: 9NQwQGbGve")
    @GET("v1/search/movie.json")
    fun requestSearchMovie(
        @Query("query") inputText: String
    )

}