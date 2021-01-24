package com.example.local.di

import com.example.data.source.MovieLocalDataSource
import com.example.local.MovieLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieLocalDataSourceModule {
    @Binds
    abstract fun bindMovieLocal(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}