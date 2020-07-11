package com.project.local.di

import androidx.room.Room
import com.project.data.NaverMovieLocalDataSource
import com.project.local.MovieDataBase
import com.project.local.impl.NaverMovieLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single<MovieDataBase> {
        Room.databaseBuilder(androidApplication(), MovieDataBase::class.java, MovieDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<MovieDataBase>().getMovieDao() }

    single<NaverMovieLocalDataSource> { NaverMovieLocalDataSourceImpl(get()) }
}