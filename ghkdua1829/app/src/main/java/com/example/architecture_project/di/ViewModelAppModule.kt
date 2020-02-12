package com.example.architecture_project.di

import com.example.architecture_project.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmAppModule = module { viewModel { MainViewModel(get()) } }