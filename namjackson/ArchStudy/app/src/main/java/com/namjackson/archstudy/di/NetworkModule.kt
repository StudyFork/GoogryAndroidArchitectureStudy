package com.namjackson.archstudy.di

import com.namjackson.archstudy.network.UpbitService
import org.koin.dsl.module

val networkModule = module {
    single { UpbitService.upbitApi }
}