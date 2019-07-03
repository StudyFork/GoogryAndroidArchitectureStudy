package com.namjackson.archstudy.di

import com.namjackson.archstudy.data.source.local.TickerLocalDataSource
import com.namjackson.archstudy.data.source.local.TickerLocalDataSourceImpl
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSource
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<TickerLocalDataSource> { TickerLocalDataSourceImpl.getInstance() }

    single<TickerRemoteDataSource> { TickerRemoteDataSourceImpl.getInstance(get()) }

}