package kr.schoolsharing.coinhelper.di

import kr.schoolsharing.coinhelper.tasks.UpbitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UpbitViewModel(get()) }
}