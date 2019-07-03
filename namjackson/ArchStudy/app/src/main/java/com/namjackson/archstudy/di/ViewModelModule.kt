package com.namjackson.archstudy.di

import com.namjackson.archstudy.view.coinlist.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { CoinListViewModel(get()) }
}