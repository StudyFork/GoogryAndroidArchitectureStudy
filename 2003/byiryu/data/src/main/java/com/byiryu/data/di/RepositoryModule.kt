package com.byiryu.data.di

import com.byiryu.data.Repository
import com.byiryu.data.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get(), get()) }
}