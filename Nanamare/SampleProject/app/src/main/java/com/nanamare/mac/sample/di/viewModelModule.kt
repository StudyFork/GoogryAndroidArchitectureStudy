package com.nanamare.mac.sample.di

import com.nanamare.mac.sample.vm.CoinViewModel
import com.nanamare.mac.sample.vm.MarketViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoinViewModel(get()) }
    viewModel { MarketViewModel(get()) }
}