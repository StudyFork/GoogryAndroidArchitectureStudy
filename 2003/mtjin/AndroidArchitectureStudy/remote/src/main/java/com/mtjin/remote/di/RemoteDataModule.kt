package com.mtjin.remote.di

import com.mtjin.remote.search.MovieRemoteDataSource
import com.mtjin.remote.search.MovieRemoteDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module


val remoteDataModule: Module = module {
    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(
            get()
        )
    }
}