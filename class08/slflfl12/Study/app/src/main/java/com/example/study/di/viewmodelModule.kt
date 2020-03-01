package com.example.study.di

import com.example.study.ui.main.MainViewModel
import org.koin.dsl.module

val viewmodelModule = module {

    single {
        MainViewModel(get())
        /*viewModel { (title: String) -> MainViewModel(title, get())}*/ //매개변수 넘겨줄 때 이렇게
    }
}