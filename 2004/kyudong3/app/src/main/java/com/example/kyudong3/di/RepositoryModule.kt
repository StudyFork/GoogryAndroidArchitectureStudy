package com.example.kyudong3.di

import com.example.kyudong3.data.repository.MovieRepository
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}