package com.example.architecture.di

import com.example.architecture.activity.search.SearchViewModel
import com.example.architecture.provider.ResourceProvider
import com.example.architecture.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
    viewModel { SearchViewModel(get(), get()) }

}