package com.jay.architecturestudy.di

import com.jay.architecturestudy.ui.WebViewViewModel
import com.jay.architecturestudy.ui.blog.BlogViewModel
import com.jay.architecturestudy.ui.image.ImageViewModel
import com.jay.architecturestudy.ui.kin.KinViewModel
import com.jay.architecturestudy.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MovieViewModel(get()) }

    viewModel { ImageViewModel(get()) }

    viewModel { BlogViewModel(get()) }

    viewModel { KinViewModel(get()) }

    viewModel { (url: String) -> WebViewViewModel(url) }
}