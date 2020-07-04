package com.tsdev.remote.di

import com.tsdev.data.source.NaverMovieRemoteSourceData
import com.tsdev.remote.NaverMovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<NaverMovieRemoteSourceData> { NaverMovieRemoteDataSourceImpl(get(), get()) }
}