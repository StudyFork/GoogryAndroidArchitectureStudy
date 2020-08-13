package com.example.study.di

import com.example.study.data.remote.RemoteDataSource
import com.example.study.data.remote.RemoteDataSourceImpl
import com.example.study.data.repository.MovieListRepository
import com.example.study.data.repository.MovieListRepositoryImpl
import com.example.study.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MovieListRepository> { MovieListRepositoryImpl(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    viewModel { MovieViewModel(get() ) }
}