package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.RepositoryInterface

val repositoryModule = module {
    single<RepositoryInterface> { Repository(get()) }
}