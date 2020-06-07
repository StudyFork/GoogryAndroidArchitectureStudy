package com.example.kyudong3.di

import com.example.kyudong3.data.remote.MovieRemoteDataSource
import com.example.kyudong3.data.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get(), get()) }
}