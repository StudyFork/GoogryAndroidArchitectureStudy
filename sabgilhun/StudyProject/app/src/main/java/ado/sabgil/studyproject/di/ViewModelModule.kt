package ado.sabgil.studyproject.di

import ado.sabgil.studyproject.view.coinlist.CoinListViewModel
import ado.sabgil.studyproject.view.home.HomeViewModel
import ado.sabgil.studyproject.view.searchcoin.SearchCoinViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SearchCoinViewModel(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel { (baseCurrency: String) ->
        CoinListViewModel(baseCurrency, get())
    }
}