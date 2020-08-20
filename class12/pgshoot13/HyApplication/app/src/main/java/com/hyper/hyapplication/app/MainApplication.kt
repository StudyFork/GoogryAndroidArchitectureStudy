package com.hyper.hyapplication.app

import android.app.Application
import com.hyper.hyapplication.BuildConfig
import com.hyper.hyapplication.module.appModule
import com.hyper.hyapplication.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MainApplication)
            modules(appModule, networkModule)
        }
    }
}