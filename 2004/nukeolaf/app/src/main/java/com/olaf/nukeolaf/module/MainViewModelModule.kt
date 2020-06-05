package com.olaf.nukeolaf.module

import com.olaf.nukeolaf.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}