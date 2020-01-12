package com.practice.achitecture.myproject

import android.app.Application
import com.practice.achitecture.myproject.di.historyViewModelModule
import com.practice.achitecture.myproject.di.mainViewModelModule
import com.practice.achitecture.myproject.di.naverRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
            androidContext(this@MyApp)
            modules(listOf(naverRepositoryModule, mainViewModelModule, historyViewModelModule))
        }
    }
}