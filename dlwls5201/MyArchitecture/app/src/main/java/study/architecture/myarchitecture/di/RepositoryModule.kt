package study.architecture.myarchitecture.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.data.repository.UpbitRepositoryImpl
import study.architecture.myarchitecture.util.NetworkUtil

val repositoryModule = module {

    single<UpbitRepository> {
        UpbitRepositoryImpl(get(), get(), NetworkUtil.isOnline(androidApplication()))
    }
}