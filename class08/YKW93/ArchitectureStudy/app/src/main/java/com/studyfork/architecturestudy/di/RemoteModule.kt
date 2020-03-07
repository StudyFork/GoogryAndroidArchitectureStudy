package com.studyfork.architecturestudy.di

import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSource
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module


val remoteModule = module {
    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(get())
    }
}