package com.example.model.di

import com.example.model.data.local.MovieLocalDataSource
import com.example.model.data.local.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localDataModule = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl() }
}