package com.studyfirstproject.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.showcoin.CoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CoinDataSource> { CoinRepository(get()) }

    @Suppress("UNCHECKED_CAST")
    viewModel { (fragmentActivity: FragmentActivity) ->
        ViewModelProviders.of(fragmentActivity, object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                CoinViewModel(get()) as T
        })[CoinViewModel::class.java]
    }
}