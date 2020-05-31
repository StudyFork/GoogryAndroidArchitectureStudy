package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.provider.ResourceProvider
import com.tsdev.tsandroid.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val resourceProviderModule = module {
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
}