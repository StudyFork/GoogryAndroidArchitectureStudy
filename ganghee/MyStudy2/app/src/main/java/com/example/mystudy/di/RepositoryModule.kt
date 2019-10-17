package com.example.mystudy.di

import com.example.mystudy.data.UpbitRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UpbitRepository(get()) }
}