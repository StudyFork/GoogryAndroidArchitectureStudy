package com.byiryu.local.di

import androidx.room.Room
import com.byiryu.data.source.local.LocalDataSource
import com.byiryu.local.model.data.AppPreference
import com.byiryu.local.model.data.LocalDataBase
import com.byiryu.local.LocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {
    single {
        AppPreference(get())
    }

    single { Room.databaseBuilder(get(), LocalDataBase::class.java, "movie.db").build() }

    single {
        get<LocalDataBase>().movieDao()
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(get<LocalDataBase>().movieDao(), get())
    }
}