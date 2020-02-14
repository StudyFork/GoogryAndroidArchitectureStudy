package com.example.handnew04.di

import com.example.handnew04.data.MovieRepository
import org.koin.dsl.module

val movieRepositoryModule = module { single { MovieRepository(get(), get()) } }