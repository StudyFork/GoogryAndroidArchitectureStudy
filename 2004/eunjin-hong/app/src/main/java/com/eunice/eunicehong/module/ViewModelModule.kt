package com.eunice.eunicehong.module

import com.eunice.eunicehong.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelDependency = module {

    viewModel { MainViewModel(androidApplication()) }

}