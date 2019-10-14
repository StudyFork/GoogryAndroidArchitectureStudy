package com.architecture.study.data.source.remote.di

import com.architecture.study.data.source.remote.CoinRemoteDataSource
import com.architecture.study.data.source.remote.CoinRemoteDataSourceImpl
import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.api.UpbitApi
import org.koin.dsl.module

val remoteModule = module {
    single<UpbitApi> { RetrofitInstance.getInstance("https://api.upbit.com") }
    single<CoinRemoteDataSource> { CoinRemoteDataSourceImpl.getInstance(get()) }
}