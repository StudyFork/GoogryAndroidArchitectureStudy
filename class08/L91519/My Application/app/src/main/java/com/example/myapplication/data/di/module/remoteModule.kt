package com.example.myapplication.data.di.module

import com.example.myapplication.MovieApi
import com.example.myapplication.data.source.remote.NaverRemoteDataSource
import com.example.myapplication.data.source.remote.NaverRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://openapi.naver.com/v1/"
private const val TIMEOUT: Long = 15

val remoteModule = module {

    factory {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MovieApi> {
        get<Retrofit>().create(
            MovieApi::class.java
        )
    }

    single<NaverRemoteDataSource> {
        NaverRemoteDataSourceImpl(get())
    }
}