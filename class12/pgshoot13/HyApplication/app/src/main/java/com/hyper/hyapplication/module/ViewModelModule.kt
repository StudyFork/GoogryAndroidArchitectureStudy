package com.hyper.hyapplication.module

import com.hyper.hyapplication.repository.NaverRepository
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSource
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl
import com.hyper.hyapplication.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<NaverRepository> { NaverRepositoryImpl(get()) }
    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl(get()) }

    viewModel { MainViewModel(get()) }
}