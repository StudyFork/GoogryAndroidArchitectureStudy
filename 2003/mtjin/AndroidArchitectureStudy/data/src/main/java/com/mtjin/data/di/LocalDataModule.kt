package com.mtjin.data.di

import androidx.room.Room
import com.mtjin.data.login.source.local.LoginLocalDataSource
import com.mtjin.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.data.search.source.local.MovieDao
import com.mtjin.data.search.source.local.MovieDatabase
import com.mtjin.data.search.source.local.MovieLocalDataSource
import com.mtjin.data.search.source.local.MovieLocalDataSourceImpl
import com.mtjin.data.utils.PreferenceManager
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