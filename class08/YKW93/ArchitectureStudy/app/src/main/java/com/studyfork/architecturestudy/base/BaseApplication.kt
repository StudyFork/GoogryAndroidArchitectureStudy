package com.studyfork.architecturestudy.base

import android.app.Application
import com.studyfork.architecturestudy.BuildConfig
import com.studyfork.architecturestudy.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@BaseApplication)
            modules(repositoryModule, remoteModule, viewModelModule, networkModule, utilModule)
        }
    }
}