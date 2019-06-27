package com.namjackson.archstudy.di

import com.namjackson.archstudy.data.source.local.TickerLocalDataSourceImpl
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single { TickerLocalDataSourceImpl.getInstance() }

    single { TickerRemoteDataSourceImpl.getInstance(get()) }

}