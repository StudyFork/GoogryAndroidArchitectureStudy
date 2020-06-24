package com.example.architecture.di

import com.example.architecture.activity.search.SearchViewModel
import com.example.architecture.data.repository.NaverRepository
import com.example.architecture.data.repository.NaverRepositoryImpl
import com.example.architecture.data.source.local.NaverLocalDataSource
import com.example.architecture.data.source.local.NaverLocalDataSourceImpl
import com.example.architecture.data.source.remote.NaverRemoteDataSource
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl
import com.example.architecture.provider.ResourceProvider
import com.example.architecture.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl() }
    single<NaverLocalDataSource> { NaverLocalDataSourceImpl(androidContext()) }
    single<NaverRepository> { NaverRepositoryImpl(get(), get()) }
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }

    viewModel { SearchViewModel(get(), get()) }
}