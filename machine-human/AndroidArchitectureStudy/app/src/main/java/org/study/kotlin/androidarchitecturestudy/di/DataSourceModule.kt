package org.study.kotlin.androidarchitecturestudy.di

import org.koin.dsl.module
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
import org.study.kotlin.androidarchitecturestudy.data.source.remote.TickerRemoteDataSource

val tickerRemoteDataSource = module {
    single{ TickerRemoteDataSource(get()) as BaseDataSource }
}