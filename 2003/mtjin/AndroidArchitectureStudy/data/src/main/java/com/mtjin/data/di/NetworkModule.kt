package com.mtjin.data.di

import com.mtjin.data.utils.NetworkManager
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single { NetworkManager(get()) }
}