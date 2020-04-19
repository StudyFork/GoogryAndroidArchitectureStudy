package io.github.sooakim.di

import io.github.sooakim.ui.login.SALoginViewModel
import io.github.sooakim.ui.movie.SAMovieSearchViewModel
import io.github.sooakim.ui.splash.SASplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SASplashViewModel(get()) }

    viewModel { SALoginViewModel(get(), get()) }

    viewModel { SAMovieSearchViewModel(get()) }
}