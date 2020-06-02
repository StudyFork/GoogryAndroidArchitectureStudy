package com.example.kyudong3.di

import com.example.kyudong3.provider.ResourceProvider
import com.example.kyudong3.provider.ResourceProviderImpl
import org.koin.dsl.module

val providerModule = module {
    factory<ResourceProvider> { ResourceProviderImpl(get()) }
}