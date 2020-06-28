package com.example.architecture.di

import com.chul.data.repository.NaverRepository
import com.chul.data.repository.NaverRepositoryImpl
import com.chul.data.source.local.NaverLocalDataSource
import com.chul.data.source.local.NaverLocalDataSourceImpl
import com.chul.data.source.remote.NaverRemoteDataSource
import com.chul.data.source.remote.NaverRemoteDataSourceImpl
import com.example.architecture.activity.search.SearchViewModel
import com.example.architecture.provider.ResourceProvider
import com.example.architecture.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl(get()) }
    single<NaverLocalDataSource> { NaverLocalDataSourceImpl(androidContext()) }
    single<NaverRepository> { NaverRepositoryImpl(get(), get()) }
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }

    viewModel { SearchViewModel(get(), get()) }
}