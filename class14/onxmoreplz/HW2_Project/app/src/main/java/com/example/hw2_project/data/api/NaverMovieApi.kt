package com.example.hw2_project.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieApi {

    @GET("v1/search//movie.json")
    fun searchMovieInApi(
        @Query("query") query: String
    ): Call<NaverMovieData.NaverMovieResponse>

    companion object{
        private const val NAVER_MOVIE_BASE_URL = "https://openapi.naver.com"

        private const val NAVER_CLIENT_ID = "fQFY7M9rMOVD2KDT8Aaq"
        private const val NAVER_CLIENT_SECRET = "v8aD8p_Ri0"

        fun create() : NaverMovieApi {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                    .build()
                return@Interceptor it.proceed(request)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(NAVER_MOVIE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverMovieApi::class.java)
        }

    }
}