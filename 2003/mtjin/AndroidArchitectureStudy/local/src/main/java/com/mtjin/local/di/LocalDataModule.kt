package com.mtjin.local.di

import androidx.room.Room
import com.mtjin.data.source.login.local.LoginLocalDataSource
import com.mtjin.local.source.login.LoginLocalDataSourceImpl
import com.mtjin.local.source.search.MovieDao
import com.mtjin.local.source.search.MovieDatabase
import com.mtjin.data.source.search.local.MovieLocalDataSource
import com.mtjin.local.source.search.MovieLocalDataSourceImpl
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