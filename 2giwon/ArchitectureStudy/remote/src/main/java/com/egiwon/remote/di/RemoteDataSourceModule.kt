package com.egiwon.remote.di

import com.egiwon.data.NaverRemoteDataSource
import com.egiwon.remote.impl.NaverRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl(get()) }
}