package study.architecture.data.di

import android.content.Context
import android.net.ConnectivityManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import study.architecture.data.repository.Repository
import study.architecture.data.repository.RepositoryImpl

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get(), get(), get()) }
    single {
        androidApplication().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
    }
}