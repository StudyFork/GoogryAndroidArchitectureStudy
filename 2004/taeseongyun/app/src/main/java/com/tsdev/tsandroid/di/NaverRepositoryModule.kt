package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.data.repository.NaverRepositoryImpl
import org.koin.dsl.module

val naverRepositoryModule = module {
    single<NaverReopsitory> { NaverRepositoryImpl(get()) }
}