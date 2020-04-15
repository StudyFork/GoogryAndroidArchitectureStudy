package com.byiryu.study.di

import com.byiryu.study.ui.mvvm.login.viewmodel.LoginViewModel
import com.byiryu.study.ui.mvvm.main.viewmodel.MainViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        LoginViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }
}