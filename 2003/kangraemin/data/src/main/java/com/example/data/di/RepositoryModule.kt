package com.example.data.di

import com.example.data.source.AuthRepository
import com.example.data.source.MovieSearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    // For MovieSearchRepository instance
    single {
        MovieSearchRepository(get(), get())
    }

    // For AuthRepository instance
    single {
        AuthRepository(get())
    }
}