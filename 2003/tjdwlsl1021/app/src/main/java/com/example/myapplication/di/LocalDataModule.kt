package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.local.source.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localDataModule = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }

    single { get<MovieDatabase>().movieDao() }

    single {
        Room.databaseBuilder(
            get(),
            MovieDatabase::class.java, "movie-database"
        )
            .allowMainThreadQueries()
            .build()
    }
}