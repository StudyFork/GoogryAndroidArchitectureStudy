package my.gong.studygong.di

import my.gong.studygong.viewmodel.CoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoinViewModel(get()) }
}