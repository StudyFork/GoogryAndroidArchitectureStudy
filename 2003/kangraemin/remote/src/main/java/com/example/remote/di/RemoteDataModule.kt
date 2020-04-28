package com.example.remote.di

import com.example.remote.source.MovieApi
import com.example.data.source.remote.MovieRemoteDataSource
import com.example.remote.source.MovieRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataModule = module {

    // For Http logging interceptor instance
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return@single interceptor
    }

    // For OkHttpClient instance
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // For GsonConverterFactory instance
    single { GsonConverterFactory.create() }

    // For RxJava2CallAdapterFactory instance
    single { RxJava2CallAdapterFactory.create() }

    // For Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    // For Movie api instance
    single { get<Retrofit>().create(MovieApi::class.java) }

    // For MovieRemoteDataSource instance
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}