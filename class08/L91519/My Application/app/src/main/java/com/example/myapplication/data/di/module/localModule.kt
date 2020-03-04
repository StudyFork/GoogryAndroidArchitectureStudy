package com.example.myapplication.data.di.module

import androidx.room.Room
import com.example.myapplication.data.MovieDatabase
import com.example.myapplication.data.source.local.NaverLocalDataSource
import com.example.myapplication.data.source.local.NaverLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies.db"
        ).allowMainThreadQueries().build()
    }

    single {
        get<MovieDatabase>().movieDao()
    }

    single<NaverLocalDataSource> {
        NaverLocalDataSourceImpl(get())
    }
}