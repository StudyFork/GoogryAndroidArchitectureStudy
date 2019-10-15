package study.architecture.myarchitecture.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import study.architecture.myarchitecture.data.source.local.LocalDataBase
import study.architecture.myarchitecture.data.source.local.UpbitLocalDataSource
import study.architecture.myarchitecture.data.source.local.UpbitLocalLocalDataSourceImpl

val localModule = module {

    single<UpbitLocalDataSource> {
        UpbitLocalLocalDataSourceImpl(get())
    }

    single {
        LocalDataBase.getInstance(androidApplication()).getUpbitDao()
    }
}