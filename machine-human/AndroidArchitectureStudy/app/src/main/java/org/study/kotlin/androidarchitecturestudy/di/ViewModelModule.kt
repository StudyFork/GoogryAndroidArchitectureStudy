package org.study.kotlin.androidarchitecturestudy.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}