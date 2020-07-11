package com.project.data.di

import com.project.data.NaverMovieRepository
import com.project.data.impl.NaverMovieRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<NaverMovieRepository> { NaverMovieRepositoryImpl(get(), get()) }

}