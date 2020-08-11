package com.example.study.di

import com.example.study.data.repository.MovieListRepository
import com.example.study.data.repository.MovieListRepositoryImpl
import com.example.study.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    single<MovieListRepository> { MovieListRepositoryImpl() }
    // MyViewModel ViewModel
    viewModel { MovieViewModel() }
}