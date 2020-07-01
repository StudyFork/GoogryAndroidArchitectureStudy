package com.example.data.module

import com.example.data.source.remote.MovieRemoteDataSource
import com.example.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}
