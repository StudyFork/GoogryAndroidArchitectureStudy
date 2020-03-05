package com.example.study.di

import com.example.study.data.repository.NaverSearchRepository
import com.example.study.data.repository.NaverSearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<NaverSearchRepository> {
        NaverSearchRepositoryImpl(get(), get())
    }
}