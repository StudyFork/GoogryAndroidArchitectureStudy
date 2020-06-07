package com.tsdev.domain.di

import com.tsdev.domain.repository.NaverMovieRepository
import com.tsdev.domain.repository.NaverMovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverMovieRepository> { NaverMovieRepositoryImpl(get(), get()) }
}