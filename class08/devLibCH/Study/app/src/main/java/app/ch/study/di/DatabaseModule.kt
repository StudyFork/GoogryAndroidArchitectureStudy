package app.ch.study.di

import app.ch.study.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().getMovieDao() }
}