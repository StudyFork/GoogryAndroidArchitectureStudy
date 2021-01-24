package com.example.remote.di

import com.example.remote.network.MovieApi
import com.example.remote.network.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MovieRetrofitModule {
    @Provides
    @Singleton
    fun provideMovieService(): MovieApiService {
        return MovieApi.movieService
    }
}