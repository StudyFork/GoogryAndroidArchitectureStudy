package com.example.remote.di

import com.example.data.source.MovieRemoteDataSource
import com.example.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieRemoteDataSourceModule {
    @Binds
    abstract fun bindMovieRemote(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}