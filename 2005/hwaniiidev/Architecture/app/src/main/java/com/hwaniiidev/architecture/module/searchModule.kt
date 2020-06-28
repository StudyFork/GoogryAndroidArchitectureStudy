package com.hwaniiidev.architecture.module

import com.hwaniiidev.architecture.ApiCall
import com.hwaniiidev.architecture.RetrofitService
import com.hwaniiidev.architecture.data.repository.NaverMovieRepository
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSource
import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSourceImpl
import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSource
import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.hwaniiidev.architecture.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val serverUrl: String = "https://openapi.naver.com/"

val searchModule = module {
    single<NaverMovieRepository>{NaverMovieRepositoryImpl(get(),get())}
    single<NaverMovieLocalDataSource>{NaverMovieLocalDataSourceImpl(androidContext())}
    single<NaverMovieRemoteDataSource>{NaverMovieRemoteDataSourceImpl(get())}
    single<RetrofitService>{Retrofit.Builder()
        .baseUrl(serverUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitService::class.java)}
    viewModel{ MainViewModel(get()) }
}