package com.example.studyforkandroid.module

import com.example.studyforkandroid.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single { MovieRemoteDataSourceImpl(get()) }
}
