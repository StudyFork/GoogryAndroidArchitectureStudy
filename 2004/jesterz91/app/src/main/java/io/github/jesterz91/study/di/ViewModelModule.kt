package io.github.jesterz91.study.di

import io.github.jesterz91.study.ui.MovieSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MovieSearchViewModel(get()) }
}