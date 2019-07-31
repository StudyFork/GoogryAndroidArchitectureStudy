package org.study.kotlin.androidarchitecturestudy

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.study.kotlin.androidarchitecturestudy.di.mainViewModelModule
import org.study.kotlin.androidarchitecturestudy.di.retrofitMoudle
import org.study.kotlin.androidarchitecturestudy.di.tickerRemoteDataSource
import org.study.kotlin.androidarchitecturestudy.di.tickerRepositoryModule

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(retrofitMoudle, tickerRemoteDataSource, tickerRepositoryModule ,mainViewModelModule))
        }
    }
}