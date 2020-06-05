package com.example.studyforkandroid.module

import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepositoryImpl(get()) }
}



