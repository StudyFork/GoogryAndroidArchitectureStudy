package com.kyudong.remote.di

import com.kyudong.data.remote.MovieRemoteDataSource
import com.kyudong.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(
            get(),
            get()
        )
    }
}