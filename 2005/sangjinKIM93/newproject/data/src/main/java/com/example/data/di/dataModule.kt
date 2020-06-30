package com.example.data.di

import com.example.data.repository.NaverMoviesRepository
import com.example.data.repository.NaverMoviesRepositoryImpl
import com.example.data.source.local.LocalDataSource
import com.example.data.source.local.LocalDataSourceImpl
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.RemoteDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {


    single<RemoteDataSource> { RemoteDataSourceImpl() }

    single<LocalDataSource> { LocalDataSourceImpl(androidApplication()) }

    single<NaverMoviesRepository> {
        NaverMoviesRepositoryImpl(get(), get())
    }

}