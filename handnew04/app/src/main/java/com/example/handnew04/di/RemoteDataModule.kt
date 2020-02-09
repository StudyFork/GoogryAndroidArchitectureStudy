package com.example.handnew04.di

import com.example.handnew04.data.remote.MovieRemoteDataSource
import com.example.handnew04.data.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module { single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl() } }
