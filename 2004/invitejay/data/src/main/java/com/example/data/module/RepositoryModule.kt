package com.example.data.module

import com.example.data.source.MovieRepository
import com.example.data.source.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}



