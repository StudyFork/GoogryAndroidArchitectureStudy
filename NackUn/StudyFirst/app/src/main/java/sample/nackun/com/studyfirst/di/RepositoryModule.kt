package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource

fun getRepositoryModule() = module {
    single {
        Repository(get())
    }

    single {
        RemoteDataSource(get())
    }
}