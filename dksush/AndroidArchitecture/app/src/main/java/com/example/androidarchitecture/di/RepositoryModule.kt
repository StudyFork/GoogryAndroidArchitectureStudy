package com.example.androidarchitecture.di

import com.example.androidarchitecture.data.repository.NaverRepoImpl
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverRepoInterface> { NaverRepoImpl(get(), get()) }
}