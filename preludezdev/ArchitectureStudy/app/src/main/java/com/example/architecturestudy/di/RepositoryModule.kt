package com.example.architecturestudy.di

import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinsRepositoryImpl(get(), get()) }
    single { CoinsRemoteDataSource(get()) }
    single { CoinsLocalDataSource() }
}