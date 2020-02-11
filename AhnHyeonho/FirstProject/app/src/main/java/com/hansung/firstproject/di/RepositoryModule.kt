package com.hansung.firstproject.di

import com.hansung.firstproject.data.repository.NaverRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { NaverRepository(get()) }
}