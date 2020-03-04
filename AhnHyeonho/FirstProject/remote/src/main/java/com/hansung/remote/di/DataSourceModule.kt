package com.hansung.remote.di

import com.hansung.remote.NaverRemoteDataSource
import com.hansung.remote.NaverRemoteDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DataSourceModule = module {
    single<NaverRemoteDataSource> {
        NaverRemoteDataSourceImpl(androidContext())
    }
}