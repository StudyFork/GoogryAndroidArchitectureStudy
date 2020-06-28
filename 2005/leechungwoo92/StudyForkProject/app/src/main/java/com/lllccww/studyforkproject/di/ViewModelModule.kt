package com.lllccww.studyforkproject.di

import com.lllccww.studyforkproject.ui.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewmodelModule = module {
    viewModel{ MainViewModel(get())}
}