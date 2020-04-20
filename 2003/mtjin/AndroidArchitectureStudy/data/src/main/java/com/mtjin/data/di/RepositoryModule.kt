package com.mtjin.data.di

import com.mtjin.data.login.source.LoginRepository
import com.mtjin.data.login.source.LoginRepositoryImpl
import com.mtjin.data.search.source.MovieRepository
import com.mtjin.data.search.source.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}