package com.project.architecturestudy.di.modules

import androidx.room.Room
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.local.room.MovieDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single<MovieDataBase> {
        Room.databaseBuilder(androidApplication(), MovieDataBase::class.java, MovieDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<MovieDataBase>().getMovieDao() }

    single<NaverMovieLocalDataSource> { NaverMovieLocalDataSourceImpl}
}