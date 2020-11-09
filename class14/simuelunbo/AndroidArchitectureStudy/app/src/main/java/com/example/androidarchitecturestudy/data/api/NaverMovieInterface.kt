package com.example.androidarchitecturestudy.data.api

import com.example.androidarchitecturestudy.data.model.MovieData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieInterface {
    @GET("/v1/search/movie.json")
    fun searchMovies(
        @Query("query") query: String,
    ): Call<MovieData>

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
        private const val CLIENT_ID = "zoFgl2SLHGQXO2WoadKj"
        private const val CLIENT_SECRET = "Tx2eCHuLZ6"

        fun create(): NaverMovieInterface {
            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader(
                        "X-Naver-Client-Id",
                        CLIENT_ID
                    )
                    .addHeader(
                        "X-Naver-Client-Secret",
                        CLIENT_SECRET
                    )
                    .build()
                return@Interceptor it.proceed(request)

            }
            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverMovieInterface::class.java)
        }

    }
}