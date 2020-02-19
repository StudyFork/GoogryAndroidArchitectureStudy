package com.example.model.di

import com.example.model.data.remote.MovieRemoteDataSource
import com.example.model.data.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module { single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl() } }
