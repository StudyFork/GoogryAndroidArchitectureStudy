package com.example.myapplication.network

import com.example.myapplication.item.SearchMovieInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object RetrofitHelper {
    var retrofit: Retrofit? = null
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            field = Retrofit.Builder()
                .baseUrl(SearchMovieServerURL.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return field
        }
        private set

    interface RequestNaverApi {
        @Headers(
            "X-Naver-Client-Id: cFYCgRzcjkYLOio3qUOT",
            "X-Naver-Client-Secret: FLB89pM53Z"
        )
        @GET(SearchMovieServerURL.URL_MOVIE)
        fun getMovieList(
            @Query("query") query: String?,  //-검색어
            @Query("display") display: Int,  //-검색 결과 최대 출력 건수
            @Query("start") start: Int,  //-검색 시작 위치
            @Query("genre") genre: String?
        ): Call<SearchMovieInfo?>? //-장르 코드
    }
}