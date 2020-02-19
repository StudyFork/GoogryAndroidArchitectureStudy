package com.hansung.repository.di

import com.hansung.repository.NaverRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { NaverRepository(get()) }
}