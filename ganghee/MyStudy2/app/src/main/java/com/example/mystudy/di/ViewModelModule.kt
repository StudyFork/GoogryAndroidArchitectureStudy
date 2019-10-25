package com.example.mystudy.di

import com.example.mystudy.viewmodel.UpbitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UpbitViewModel(get()) }
}