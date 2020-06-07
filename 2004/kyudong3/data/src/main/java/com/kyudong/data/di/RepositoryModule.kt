package com.kyudong.data.di

import com.kyudong.data.repository.MovieRepository
import com.kyudong.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}