package com.example.myapplication.di

import com.example.myapplication.data.remote.api.NaverMovieApi
import com.example.myapplication.data.remote.source.MovieRemoteDataSource
import com.example.myapplication.data.remote.source.MovieRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val remoteDataModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return@single interceptor
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(NaverMovieApi::class.java) }

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }

}