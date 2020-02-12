package com.example.androidarchitecture.di

import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceImpl
import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceInterface
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDataSourceInterface
import org.koin.dsl.module

val dataSoureceModule = module {

    single<NaverRemoteDataSourceInterface> { NaverRemoteDataSourceImpl() }
    single<NaverLocalDataSourceInterface> { NaverLocalDataSourceImpl(get(), get()) }

}