package com.byiryu.local.di

import com.byiryu.data.source.local.LocalDataSource
import com.byiryu.local.model.data.AppPreference
import com.byiryu.local.model.data.LocalDataBase
import com.byiryu.local.LocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {
    single {
        AppPreference(get())
    }

    single {
        LocalDataBase.getInstance(get())
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(get<LocalDataBase>().movieDao(), get())
    }
}