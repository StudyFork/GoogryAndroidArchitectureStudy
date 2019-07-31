package com.studyfirstproject.di

import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.showcoin.CoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { CoinRepository(get()) }

    viewModel { CoinViewModel(get()) }
}