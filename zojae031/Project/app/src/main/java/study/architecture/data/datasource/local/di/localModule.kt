package study.architecture.data.datasource.local.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import study.architecture.data.datasource.local.CoinDatabase
import study.architecture.data.datasource.local.LocalDataSource
import study.architecture.data.datasource.local.LocalDataSourceImpl

val localModule = module {
    single<LocalDataSource> { LocalDataSourceImpl.getInstance(get()) }
    single { CoinDatabase.getInstance(androidContext()) }

}
