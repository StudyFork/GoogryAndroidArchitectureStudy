package com.example.local.di

import androidx.room.Room
import com.example.data2.soruce.local.MovieLocalDataSource
import com.example.local.data.MovieDatabase
import com.example.local.source.MovieLocalDataSourceImpl

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