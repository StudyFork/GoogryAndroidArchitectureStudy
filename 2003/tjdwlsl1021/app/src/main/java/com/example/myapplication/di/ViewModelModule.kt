package com.example.myapplication.di

import com.example.myapplication.ui.SearchMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchMovieViewModel(get()) }
}