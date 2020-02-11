package com.hansung.firstproject.di

import com.hansung.firstproject.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module { viewModel { MainViewModel(get()) } }