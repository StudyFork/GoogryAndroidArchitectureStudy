package com.architecture.study.data.repository.di

import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.repository.CoinRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CoinRepository> { CoinRepositoryImpl(get()) }
}