package com.chul.data.di

import com.chul.data.repository.NaverRepository
import com.chul.data.repository.NaverRepositoryImpl
import com.chul.data.source.local.NaverLocalDataSource
import com.chul.data.source.local.NaverLocalDataSourceImpl
import com.chul.data.source.remote.NaverRemoteDataSource
import com.chul.data.source.remote.NaverRemoteDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {

    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl(get()) }
    single<NaverLocalDataSource> { NaverLocalDataSourceImpl(androidContext()) }
    single<NaverRepository> { NaverRepositoryImpl(get(), get()) }
}