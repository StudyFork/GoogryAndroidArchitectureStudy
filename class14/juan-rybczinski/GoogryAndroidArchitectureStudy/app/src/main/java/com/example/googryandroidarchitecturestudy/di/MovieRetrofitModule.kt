package com.example.googryandroidarchitecturestudy.di

import com.example.googryandroidarchitecturestudy.network.MovieApi
import com.example.googryandroidarchitecturestudy.network.MovieApiService
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