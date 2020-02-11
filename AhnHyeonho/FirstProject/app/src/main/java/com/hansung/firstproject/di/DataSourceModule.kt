package com.hansung.firstproject.di

import com.hansung.firstproject.data.source.remote.NaverRemoteDataSource
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val DataSourceModule = module {
    single<NaverRemoteDataSource> {
        NaverRemoteDataSourceImpl(androidContext())
    }
}