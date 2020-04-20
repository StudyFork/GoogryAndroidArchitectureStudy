package com.mtjin.local.di

import androidx.room.Room
import com.mtjin.local.login.LoginLocalDataSource
import com.mtjin.local.login.LoginLocalDataSourceImpl
import com.mtjin.local.search.MovieDao
import com.mtjin.local.search.MovieDatabase
import com.mtjin.local.search.MovieLocalDataSource
import com.mtjin.local.search.MovieLocalDataSourceImpl
import com.mtjin.local.utils.PreferenceManager
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataModule: Module = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }
    single<PreferenceManager> { PreferenceManager(get()) }
    single<MovieDao> { get<MovieDatabase>().movieDao() }
    single<MovieDatabase> {
        Room.databaseBuilder(
                get(),
                MovieDatabase::class.java, "Movie.db"
            )
            .allowMainThreadQueries()
            .build()
    }
}