package com.hwaniiidev.architecture.module

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

val searchModule = module {
    single<NaverMovieRepository>{NaverMovieRepositoryImpl(get(),get())}
    single<NaverMovieLocalDataSource>{NaverMovieLocalDataSourceImpl(androidContext())}
    single<NaverMovieRemoteDataSource>{NaverMovieRemoteDataSourceImpl()}
    viewModel{ MainViewModel(get()) }
}