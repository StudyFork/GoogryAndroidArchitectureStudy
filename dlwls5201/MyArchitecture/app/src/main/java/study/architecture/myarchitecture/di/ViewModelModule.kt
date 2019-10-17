package study.architecture.myarchitecture.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import study.architecture.myarchitecture.ui.main.MainViewModel
import study.architecture.myarchitecture.ui.tickerlist.TickerListViewModel

val viewModelModule = module {

    viewModel {
        MainViewModel(get())
    }

    viewModel { (keyMarket: String) ->
        TickerListViewModel(get(), keyMarket)
    }
}