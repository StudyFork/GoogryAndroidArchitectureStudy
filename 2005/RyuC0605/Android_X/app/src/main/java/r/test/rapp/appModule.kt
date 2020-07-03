package r.test.rapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import r.test.rapp.main.MainViewModel

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
}

