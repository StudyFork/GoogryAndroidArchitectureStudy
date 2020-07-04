package com.tsdev.data.di

import com.tsdev.data.MovieRepositoryImpl
import com.tsdev.domain.repository.NaverMovieRepository
import org.koin.dsl.module

val naverMovieRepository = module {
    single<NaverMovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
}