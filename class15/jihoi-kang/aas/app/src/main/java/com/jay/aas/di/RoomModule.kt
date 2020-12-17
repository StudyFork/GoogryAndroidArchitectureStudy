package com.jay.aas.di

import android.content.Context
import androidx.room.Room
import com.jay.aas.api.MovieService
import com.jay.aas.api.RequestInterceptor
import com.jay.aas.room.AppDatabase
import com.jay.aas.room.MovieDao
import com.jay.aas.room.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

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