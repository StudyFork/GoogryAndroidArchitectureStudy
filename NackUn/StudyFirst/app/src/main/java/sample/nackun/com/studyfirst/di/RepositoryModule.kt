package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.RepositoryImpl
import sample.nackun.com.studyfirst.data.RepositoryInterface

val repositoryModule = module {
    single<RepositoryInterface> { RepositoryImpl(get()) }
}