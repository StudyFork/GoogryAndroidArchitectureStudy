package com.example.studyforkandroid.module

import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import com.example.studyforkandroid.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepositoryImpl(get()) }
}

val remoteDataModule = module {
    single { MovieRemoteDataSourceImpl(get()) }
}


