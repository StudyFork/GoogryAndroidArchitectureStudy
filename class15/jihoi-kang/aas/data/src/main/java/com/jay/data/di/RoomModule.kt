package com.jay.data.di

import android.content.Context
import androidx.room.Room
import com.jay.data.room.AppDatabase
import com.jay.data.room.MovieDao
import com.jay.data.room.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class RoomModule {

    @Provides
    @Reusable
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Movie.db",
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Reusable
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao =
        appDatabase.movieDao()

    @Provides
    @Reusable
    fun provideSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao =
        appDatabase.searchHistoryDao()

}