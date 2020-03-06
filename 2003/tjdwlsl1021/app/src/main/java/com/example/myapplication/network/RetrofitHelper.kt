package com.example.myapplication.network

import android.util.Log
import com.example.myapplication.item.SearchMovieInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



/**
 * val : immutable이라서 생성한 후에는 값을 변경할 수 없다, 생성할 때 값이 있어야 한다.
 * lazy : 늦은 초기화, 호출 시점에 초기화를 진행한다, 호출할 때 한번만 초기화하고, 이후부터는 가져다가 쓴다.
 * lazy 옵션 3개(SYNCHRONIZED, PUBLICATION,NONE), default는 SYNCHRONIZED
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
        ): Call<SearchMovieInfo?>?
    }
}