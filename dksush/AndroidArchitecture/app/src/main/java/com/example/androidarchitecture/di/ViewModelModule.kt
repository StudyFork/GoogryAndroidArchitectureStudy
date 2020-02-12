package com.example.androidarchitecture.di

import com.example.androidarchitecture.ui.blog.BlogViewModel
import com.example.androidarchitecture.ui.image.ImageViewModel
import com.example.androidarchitecture.ui.kin.KinViewModel
import com.example.androidarchitecture.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        BlogViewModel(get())
    }
    viewModel {
        MovieViewModel(get())
    }
    viewModel {
        ImageViewModel(get())
    }
    viewModel {
        KinViewModel(get())
    }


}
