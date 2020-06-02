package com.example.kyudong3.di

import androidx.room.Room
import com.example.kyudong3.data.local.MovieDatabase
import com.example.kyudong3.data.local.MovieLocalDataSource
import com.example.kyudong3.data.local.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localDataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            MovieDatabase::class.java,
            "movie-db"
        ).build()
    }

    single {
       get<MovieDatabase>().movieDao()
    }

    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get(), get()) }
}