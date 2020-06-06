package com.example.studyforkandroid.module

import com.example.studyforkandroid.data.source.MovieRepository
import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}



