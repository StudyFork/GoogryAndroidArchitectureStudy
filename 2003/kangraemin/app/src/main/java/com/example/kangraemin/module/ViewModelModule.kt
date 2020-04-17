package com.example.kangraemin.module

import com.example.kangraemin.ui.login.LoginViewModel
import com.example.kangraemin.ui.main.MainViewModel
import com.example.kangraemin.ui.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {

    // For SplashViewModel instance
    single {
        SplashViewModel(get())
    }

    // For LoginViewModel instance
    single {
        LoginViewModel(get())
    }

    // For MainViewModel instance
    single {
        MainViewModel(get(), get(), get())
    }
}