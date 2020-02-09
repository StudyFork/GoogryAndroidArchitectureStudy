package com.example.androidarchitecture.di

import com.example.androidarchitecture.ui.blog.BlogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        BlogViewModel(
            get()
        )
    }
}
