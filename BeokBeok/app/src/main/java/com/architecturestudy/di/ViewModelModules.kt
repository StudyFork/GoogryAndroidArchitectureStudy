package com.architecturestudy.di

import com.architecturestudy.upbitmarket.UpbitViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { UpbitViewModel(get()) }
}