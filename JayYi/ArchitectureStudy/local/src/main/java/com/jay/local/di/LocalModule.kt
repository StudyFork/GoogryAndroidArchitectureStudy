package com.jay.local.di

import com.jay.local.NaverSearchLocalDataSource
import com.jay.local.NaverSearchLocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {
    single<NaverSearchLocalDataSource> { NaverSearchLocalDataSourceImpl(get(), get()) }
}