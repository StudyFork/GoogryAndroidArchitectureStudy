package com.example.model.di

import com.example.model.data.MovieRepository
import org.koin.dsl.module

val movieRepositoryModule = module { single { MovieRepository(get(), get()) } }