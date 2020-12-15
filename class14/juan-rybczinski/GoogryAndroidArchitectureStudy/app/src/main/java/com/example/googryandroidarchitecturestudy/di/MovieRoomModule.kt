package com.example.googryandroidarchitecturestudy.di

import android.content.Context
import com.example.googryandroidarchitecturestudy.database.MovieDao
import com.example.googryandroidarchitecturestudy.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MovieRoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.getInstance(context)
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao
    }
}