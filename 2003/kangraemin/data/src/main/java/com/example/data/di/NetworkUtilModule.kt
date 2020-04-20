package com.example.data.di

import com.example.data.source.NetworkUtil
import org.koin.dsl.module

val networkUtilModule = module {

    // For NetworkUtil
    single {
        com.example.data.source.NetworkUtil(get())
    }
}