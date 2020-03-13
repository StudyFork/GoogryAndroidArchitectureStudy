package com.example.kangraemin.util

import com.example.kangraemin.model.remote.datadao.MovieInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    fun getClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    companion object {
        private var movieSearchApi: MovieInterface? = null

        fun getMovieApi(): MovieInterface {
            if (movieSearchApi == null) {
                movieSearchApi = RetrofitClient()
                    .getClient("https://openapi.naver.com")
                    .create(MovieInterface::class.java)
            }
            return movieSearchApi!!
        }
    }
}