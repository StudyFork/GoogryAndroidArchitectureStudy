package com.namjackson.archstudy.di

import com.namjackson.archstudy.data.source.TickerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TickerRepository.getInstance(get(), get()) }
}