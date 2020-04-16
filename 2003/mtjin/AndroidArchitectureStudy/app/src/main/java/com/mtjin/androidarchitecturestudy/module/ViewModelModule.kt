package com.mtjin.androidarchitecturestudy.module

import com.mtjin.androidarchitecturestudy.ui.login.LoginViewModel
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchViewModel
import com.mtjin.androidarchitecturestudy.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MovieSearchViewModel(get()) }
}