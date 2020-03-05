package com.cnm.homework.di

import android.app.Application
import com.cnm.homework.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainAppication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@MainAppication)
            modules(
                localModule, remoteModule, repositoryModule, viewModelModule
            )
        }
    }
}