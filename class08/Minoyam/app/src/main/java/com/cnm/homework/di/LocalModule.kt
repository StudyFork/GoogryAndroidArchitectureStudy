package com.cnm.homework.di

import androidx.room.Room
import com.cnm.homework.data.source.local.NaverQueryLocalDataSource
import com.cnm.homework.data.source.local.NaverQueryLocalDataSourceImpl
import com.cnm.homework.data.source.local.db.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            LocalDatabase::class.java,
            "db.db"
        ).allowMainThreadQueries().build()
    }
    single {
        get<LocalDatabase>().localDao()
    }
    single<NaverQueryLocalDataSource> {
        NaverQueryLocalDataSourceImpl(get())
    }
}