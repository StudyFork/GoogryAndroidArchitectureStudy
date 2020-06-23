package com.project.architecturestudy.di.modules

import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<NaverMovieRemoteDataSource> { NaverMovieRemoteDataSourceImpl }
}