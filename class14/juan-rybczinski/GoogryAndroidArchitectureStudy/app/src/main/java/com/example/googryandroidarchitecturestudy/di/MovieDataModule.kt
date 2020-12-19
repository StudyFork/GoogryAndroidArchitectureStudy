package com.example.googryandroidarchitecturestudy.di

import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSource
import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSource
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieDataModule {
    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieLocal(impl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    abstract fun bindMovieRemote(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}