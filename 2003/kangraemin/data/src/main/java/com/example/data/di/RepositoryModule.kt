package com.example.data.di

import com.example.data.source.AuthRepository
import com.example.data.source.AuthRepositoryImpl
import com.example.data.source.MovieSearchRepository
import com.example.data.source.MovieSearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    // For MovieSearchRepository instance
    single<MovieSearchRepository> { MovieSearchRepositoryImpl(get(), get()) }

    // For AuthRepository instance
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}