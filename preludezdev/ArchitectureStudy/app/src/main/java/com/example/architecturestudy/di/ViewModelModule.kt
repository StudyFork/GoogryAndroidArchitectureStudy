package com.example.architecturestudy.di

import com.example.architecturestudy.viewmodel.MainViewModel
import com.example.architecturestudy.viewmodel.MarketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { (keyMarket: String) -> MarketViewModel(get(), keyMarket) }
}