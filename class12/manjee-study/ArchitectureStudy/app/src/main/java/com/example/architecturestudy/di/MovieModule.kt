package com.example.architecturestudy.di

import com.example.architecturestudy.data.repository.MovieRepository
import com.example.architecturestudy.data.repository.MovieRespositoryImpl
import com.example.architecturestudy.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {

    single<MovieRepository> { MovieRespositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}