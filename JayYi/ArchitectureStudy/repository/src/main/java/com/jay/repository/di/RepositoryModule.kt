package com.jay.repository.di

import com.jay.repository.repository.NaverSearchRepository
import com.jay.repository.repository.NaverSearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverSearchRepository> { NaverSearchRepositoryImpl(get(), get()) }
}
