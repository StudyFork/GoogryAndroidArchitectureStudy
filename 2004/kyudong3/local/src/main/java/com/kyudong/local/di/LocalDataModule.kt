package com.kyudong.local.di

import androidx.room.Room
import com.kyudong.data.local.MovieLocalDataSource
import com.kyudong.local.db.MovieDatabase
import com.kyudong.local.db.MovieLocalDataSourceImpl
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

    single<MovieLocalDataSource> {
        MovieLocalDataSourceImpl(
            get(),
            get()
        )
    }
}