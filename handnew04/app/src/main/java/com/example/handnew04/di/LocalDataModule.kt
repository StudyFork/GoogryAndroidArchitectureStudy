package com.example.handnew04.di

import com.example.handnew04.data.local.MovieLocalDataSource
import com.example.handnew04.data.local.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localDataModule = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl() }
}