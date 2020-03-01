package com.example.study.di

import com.example.study.data.source.remote.NaverSearchRemoteDataSource
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.data.source.remote.network.NaverApiClient
import com.example.study.data.source.remote.network.NaverApiService
import com.example.study.data.source.remote.network.interceptor.ApplicationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://openapi.naver.com/"

val remoteModule = module {


    single {
        ApplicationInterceptor()
    }

    single {
        OkHttpClient().newBuilder() //.build() 해야 OkHttpClient가 됨
            .addInterceptor(get<ApplicationInterceptor>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        RxJava2CallAdapterFactory.create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get<OkHttpClient>())
            .build()
    }

    single<NaverApiService> {
        get<Retrofit>().create(NaverApiService::class.java)
    }

    single<NaverSearchRemoteDataSource> {
        NaverSearchRemoteDataSourceImpl()
    }
}