package com.olaf.nukeolaf.module

import com.olaf.nukeolaf.data.local.MovieLocalDataSource
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSource
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepository
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val movieModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(androidContext()) }
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}