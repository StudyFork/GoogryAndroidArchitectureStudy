package study.architecture.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import study.architecture.coinjob.CoinFragment
import study.architecture.coinjob.CoinViewModel
import study.architecture.mainjob.MainViewModel

val viewModelModule = module {
    viewModel { (index: CoinFragment.FragIndex) ->
        CoinViewModel(
            get(),
            index
        )
    }

    viewModel { MainViewModel() }
}