package com.cnm.homework.di

import com.cnm.homework.data.repository.NaverQueryRepository
import com.cnm.homework.data.repository.NaverQueryRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverQueryRepository> {
        NaverQueryRepositoryImpl(get(), get())
    }
}