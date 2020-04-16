package com.mtjin.androidarchitecturestudy.module

import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.search.source.MovieRepository
import com.mtjin.androidarchitecturestudy.data.search.source.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}