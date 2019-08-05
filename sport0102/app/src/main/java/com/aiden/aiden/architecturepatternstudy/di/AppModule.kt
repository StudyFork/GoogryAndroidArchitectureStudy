package com.aiden.aiden.architecturepatternstudy.di

import com.aiden.aiden.architecturepatternstudy.ui.main.MainSearchViewModel
import com.aiden.aiden.architecturepatternstudy.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getAppModule() = module {
    viewModel { MainSearchViewModel() }
    viewModel { MainViewModel(get()) }
}