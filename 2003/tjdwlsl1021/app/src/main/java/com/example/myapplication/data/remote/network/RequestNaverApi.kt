package com.example.myapplication.data.remote.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//interface RequestNaverApi {
//    @Headers(
//        "X-Naver-Client-Id: cFYCgRzcjkYLOio3qUOT",
//        "X-Naver-Client-Secret: FLB89pM53Z"
//    )
//    @GET(ServerURL.URL_MOVIE)
//    fun getMovieList(
//        @Query("query") query: String,  //-검색어
//        @Query("display") display: Int,  //-검색 결과 최대 출력 건수
//        @Query("start") start: Int,  //-검색 시작 위치
//        @Query("genre") genre: String //-장르 코드
//    ): Call<SearchMovieInfoResponse>
//}