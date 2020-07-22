package com.hwaniiidev.architecture.module

import com.hwaniiidev.architecture.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModel = module {
    viewModel{ MainViewModel(get()) }
}