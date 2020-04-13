package com.mtjin.androidarchitecturestudy.module

import androidx.room.Room
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.data.search.source.local.MovieDao
import com.mtjin.androidarchitecturestudy.data.search.source.local.MovieDatabase
import com.mtjin.androidarchitecturestudy.data.search.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.search.source.local.MovieLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataModule: Module = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }
    single<PreferenceManager> { PreferenceManager(androidContext()) }
    single<MovieDao> { get<MovieDatabase>().movieDao() }
    single<MovieDatabase> {
        Room.databaseBuilder(
                androidContext(),
                MovieDatabase::class.java, "Movie.db"
            )
            .allowMainThreadQueries()
            .build()
    }
}