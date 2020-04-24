package com.example.local.di

import androidx.room.Room
import com.example.data.source.local.AuthLocalDataSource
import com.example.data.source.local.LocalMovieDataSource
import com.example.local.source.*
import org.koin.dsl.module

val localDataModule = module {

    // For AppDatabase instance
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "local"
        ).build()
    }

    // For authDao instance
    single { get<AppDatabase>().authDao() }

    // For AuthLocalDataSource instance
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(get()) }

    // For MovieDao instance
    single { get<AppDatabase>().movieDao() }

    // For MovieRemoteDataSource instance
    single<LocalMovieDataSource> { LocalMovieDataSourceImpl(get()) }

}