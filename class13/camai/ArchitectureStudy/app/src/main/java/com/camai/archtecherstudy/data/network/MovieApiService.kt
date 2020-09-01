package com.camai.archtecherstudy.data.network

import com.bumptech.glide.BuildConfig
import com.camai.archtecherstudy.data.model.MovieResponseModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApiService {

    @GET("v1/search/movie.json")
    fun getMovieSearch(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<MovieResponseModel>

    companion object {
        private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
        private const val CLIENT_ID = "IgChPWP2oMYZzitYv0rW"
        private const val CLIENT_SECRET = "KKfBdZH0ZC"
        private const val CONNECT_TIMEOUT: Long = 15

        fun create(): MovieApiService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()

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

            if (BuildConfig.DEBUG)
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_NAVER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApiService::class.java)
        }
    }
}