package com.example.local.di

import android.content.Context
import com.example.local.database.MovieDao
import com.example.local.database.MovieDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): com.example.local.database.MovieDatabase {
        return com.example.local.database.MovieDatabase.getInstance(context)
    }

    @Provides
    fun provideMovieDao(database: com.example.local.database.MovieDatabase): com.example.local.database.MovieDao {
        return database.movieDao
    }
}