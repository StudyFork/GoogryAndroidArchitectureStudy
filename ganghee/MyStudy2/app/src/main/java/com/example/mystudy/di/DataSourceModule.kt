package com.example.mystudy.di

import com.example.mystudy.data.remote.UpbitRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        UpbitRemoteDataSource(get())
    }
}