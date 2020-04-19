package com.example.myapplication.di

import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}