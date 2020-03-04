package com.example.myapplication.data.di.module

import com.example.myapplication.data.repository.NaverRepository
import com.example.myapplication.data.repository.NaverRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverRepository> {
        NaverRepositoryImpl(get(), get())
    }
}
