package com.architecture.study.data.source.remote.di

import com.architecture.study.data.source.remote.CoinRemoteDataSource
import com.architecture.study.data.source.remote.CoinRemoteDataSourceImpl
import com.architecture.study.network.api.UpbitApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {
    single<UpbitApi> { get<Retrofit> { parametersOf("https://api.upbit.com") }.create(UpbitApi::class.java) }
    single<CoinRemoteDataSource> { CoinRemoteDataSourceImpl(get()) }
}