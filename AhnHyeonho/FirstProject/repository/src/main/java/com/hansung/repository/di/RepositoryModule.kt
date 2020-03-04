package com.hansung.repository.di

import com.hansung.repository.NaverRepository
import com.hansung.repository.NaverRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {
    single<NaverRepository> {
        NaverRepositoryImpl(get())
    }
}