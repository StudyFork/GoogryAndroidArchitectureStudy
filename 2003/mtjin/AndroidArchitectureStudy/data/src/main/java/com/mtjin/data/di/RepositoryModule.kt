package com.mtjin.data.di

import com.mtjin.data.source.login.LoginRepository
import com.mtjin.data.source.login.LoginRepositoryImpl
import com.mtjin.data.source.search.MovieRepository
import com.mtjin.data.source.search.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}