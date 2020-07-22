package com.hwaniiidev.data

import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSourceImpl
import com.hwaniiidev.data.repository.NaverMovieRepository
import com.hwaniiidev.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.data.source.local.NaverMovieLocalDataSource
import com.hwaniiidev.data.source.remote.NaverMovieRemoteDataSource
import com.hwaniiidev.data.source.remote.NaverMovieRemoteDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val serverUrl: String = "https://openapi.naver.com/"

val dataModule = module {
    single<NaverMovieRepository> { NaverMovieRepositoryImpl(get(), get()) }
    single<NaverMovieLocalDataSource> { NaverMovieLocalDataSourceImpl(androidContext()) }
    single<NaverMovieRemoteDataSource> { NaverMovieRemoteDataSourceImpl(get()) }
    single<RetrofitService> {
        Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}