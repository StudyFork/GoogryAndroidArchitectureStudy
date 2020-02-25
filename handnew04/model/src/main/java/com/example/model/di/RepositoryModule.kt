package com.example.model.di

import com.example.model.data.MovieRepositoryImpl
import org.koin.dsl.module

val movieRepositoryModule = module { single { MovieRepositoryImpl(get(), get()) } }