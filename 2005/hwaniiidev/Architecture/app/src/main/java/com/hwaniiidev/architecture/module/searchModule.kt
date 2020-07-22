package com.hwaniiidev.architecture.module

import com.hwaniiidev.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.ui.main.MainViewModel
import com.hwaniiidev.data.repository.NaverMovieRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single<NaverMovieRepository>{
        NaverMovieRepositoryImpl(
            get(),
            get()
        )
    }
    viewModel{ MainViewModel(get()) }
}