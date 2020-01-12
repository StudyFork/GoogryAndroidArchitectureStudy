package com.practice.achitecture.myproject.di

import com.practice.achitecture.myproject.history.HistoryViewModel
import com.practice.achitecture.myproject.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val historyViewModelModule = module {
    viewModel { HistoryViewModel(get()) }
}