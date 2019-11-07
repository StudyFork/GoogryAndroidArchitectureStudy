package com.egiwon.architecturestudy.data.source.service

import com.egiwon.architecturestudy.BuildConfig
import com.egiwon.architecturestudy.data.source.remote.ContentsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/v1/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader(
                                "X-Naver-Client-Id",
                                API_ID
                            )
                            .addHeader(
                                "X-Naver-Client-Secret",
                                API_SECRET
                            )
                            .build()
                    )

                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ContentsService::class.java)

    private const val API_ID = "N6fr8OFPNCzX7SVctnkG"
    private const val API_SECRET = "WHmQ8WtbYf"
}