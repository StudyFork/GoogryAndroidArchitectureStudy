package com.egiwon.data.di

import com.egiwon.data.NaverDataRepository
import com.egiwon.data.impl.NaverDataRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<NaverDataRepository> { NaverDataRepositoryImpl(get(), get()) }
}