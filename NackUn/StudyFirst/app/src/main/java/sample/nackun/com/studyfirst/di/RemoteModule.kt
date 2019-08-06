package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource

val remoteModule = module {
    single<DataSource> { RemoteDataSource(get()) }
}