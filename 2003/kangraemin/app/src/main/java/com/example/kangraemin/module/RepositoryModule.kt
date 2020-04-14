package com.example.kangraemin.module

import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
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