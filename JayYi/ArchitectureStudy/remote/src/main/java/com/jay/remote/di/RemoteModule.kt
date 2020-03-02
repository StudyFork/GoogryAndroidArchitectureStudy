package com.jay.remote.di

import com.jay.remote.NaverSearchRemoteDataSource
import com.jay.remote.NaverSearchRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<NaverSearchRemoteDataSource> { NaverSearchRemoteDataSourceImpl(get()) }
}