package com.example.handnew04.di

import com.example.handnew04.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module { viewModel { MainViewModel(get(), get()) } }