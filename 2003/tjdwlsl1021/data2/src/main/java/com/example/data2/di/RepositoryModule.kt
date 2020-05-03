package com.example.data2.di

import com.example.data2.MovieRepository
import com.example.data2.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}