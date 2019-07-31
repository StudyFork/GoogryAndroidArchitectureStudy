package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource

val RemoteModule = module {
    single { RemoteDataSource(get()) }
}