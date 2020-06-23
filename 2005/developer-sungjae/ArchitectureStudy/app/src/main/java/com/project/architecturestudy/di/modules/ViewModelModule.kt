package com.project.architecturestudy.di.modules

import com.project.architecturestudy.Provider.ResourceProvider
import com.project.architecturestudy.Provider.ResourceProviderImpl
import com.project.architecturestudy.data.repository.NaverMovieRepository
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single<NaverMovieRepository> { NaverMovieRepositoryImpl(get(), get()) }

    single<ResourceProvider> { ResourceProviderImpl(androidApplication()) }

    viewModel { SearchViewModel(get(), get()) }
}