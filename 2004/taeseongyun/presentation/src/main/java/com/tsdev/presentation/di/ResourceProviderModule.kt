package com.tsdev.presentation.di

import com.tsdev.presentation.provider.ResourceProvider
import com.tsdev.presentation.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val resourceProviderModule = module {
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
}