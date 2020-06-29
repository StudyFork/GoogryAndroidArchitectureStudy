package com.project.remote.di

import com.project.data.NaverMovieRemoteDataSource
import com.project.remote.impl.NaverMovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<NaverMovieRemoteDataSource> { NaverMovieRemoteDataSourceImpl(get()) }
}