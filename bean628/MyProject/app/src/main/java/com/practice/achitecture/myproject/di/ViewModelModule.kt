package com.practice.achitecture.myproject.di

import com.practice.achitecture.myproject.history.HistoryViewModel
import com.practice.achitecture.myproject.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}
