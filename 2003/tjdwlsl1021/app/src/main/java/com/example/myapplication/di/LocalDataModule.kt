package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.local.source.MovieLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }

    single { get<MovieDatabase>().movieDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie-database"
        )
            .allowMainThreadQueries()
            .build()
    }
}