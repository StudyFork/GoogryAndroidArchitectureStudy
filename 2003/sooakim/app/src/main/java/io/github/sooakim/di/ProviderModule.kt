package io.github.sooakim.di

import io.github.sooakim.util.ResourceProvider
import io.github.sooakim.util.ResourceProviderImpl
import org.koin.dsl.module

val providerModule = module {
    single<ResourceProvider> { ResourceProviderImpl(get()) }
}