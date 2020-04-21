package com.example.data.di

import com.example.data.source.NetworkUtil
import com.example.data.source.NetworkUtilImpl
import org.koin.dsl.module

val networkUtilModule = module {

    // For NetworkUtil
    single<NetworkUtil> { NetworkUtilImpl(get()) }
}