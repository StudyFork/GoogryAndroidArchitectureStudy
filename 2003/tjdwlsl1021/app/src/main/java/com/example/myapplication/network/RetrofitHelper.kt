package com.example.myapplication.network

import android.util.Log
import com.example.myapplication.model.RSP_SearchMovieInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



/**
 * Retrofit 세팅
 * */
object RetrofitHelper {

    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SearchMovieServerURL.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val retrofitService: RequestNaverApi by lazy {
        Log.d("RetrofitHelper","호출")
        retrofit.create(RequestNaverApi::class.java)
    }

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
            @Query("genre") genre: String? //-장르 코드
        ): Call<RSP_SearchMovieInfo?>?
    }
}