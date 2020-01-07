package app.ch.study.di

import app.ch.study.viewmodel.MovieViewModelFactory
import org.koin.dsl.module

val viewModelModule = module {
    single {
        MovieViewModelFactory(get(), get())
    }
}