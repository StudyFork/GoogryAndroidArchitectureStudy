package com.sangjin.newproject.di

import com.sangjin.newproject.MovieListViewModel
import com.sangjin.newproject.data.repository.NaverMoviesRepository
import com.sangjin.newproject.data.repository.NaverMoviesRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSource
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.remote.RemoteDataSource
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import com.sangjin.newproject.utils.ResourceProvider
import com.sangjin.newproject.utils.ResourceProviderImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<RemoteDataSource> { RemoteDataSourceImpl() }

    single<LocalDataSource> { LocalDataSourceImpl(androidApplication()) }

    single<NaverMoviesRepository> { NaverMoviesRepositoryImpl(get(), get()) }

    single<ResourceProvider> {ResourceProviderImpl(androidApplication())}

    viewModel { MovieListViewModel(get(), get()) }

}