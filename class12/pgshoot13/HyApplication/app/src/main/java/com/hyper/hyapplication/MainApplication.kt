package com.hyper.hyapplication

import android.app.Application
import com.hyper.hyapplication.module.movieModule
import com.hyper.hyapplication.module.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(movieModule, remoteModule)
        }
    }
}