package com.camai.archtecherstudy.data.network

import com.camai.archtecherstudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieApiServiceImpl {

    private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
    private const val CLIENT_ID = "IgChPWP2oMYZzitYv0rW"
    private const val CLIENT_SECRET = "KKfBdZH0ZC"
    private const val CONNECT_TIMEOUT: Long = 15
    private var movieApiService: MovieApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = BODY
        } else {
            httpLoggingInterceptor.level = NONE
        }

        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                .build()
            return@Interceptor it.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val create = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApiService = create.create(MovieApiService::class.java)
    }

    fun buildService(): MovieApiService {
        return movieApiService
    }


}