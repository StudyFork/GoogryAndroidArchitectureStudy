package com.example.study.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.study.data.source.local.NaverSearchLocalDataSource
import com.example.study.data.source.local.NaverSearchLocalDataSourceImpl
import com.example.study.data.source.local.SearchResultDao
import com.example.study.data.source.local.SearchResultDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localModule = module {


    single {
        Room.databaseBuilder(androidContext(), SearchResultDatabase::class.java, "SearchResult.db")
            .allowMainThreadQueries().build()
    }

    single {
        get<SearchResultDatabase>().searchResultDao()
    }

    single<NaverSearchLocalDataSource> {
        NaverSearchLocalDataSourceImpl(get())
    }
}