package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.Repository

val repositoryModule = module {
    single { Repository(get()) }
}