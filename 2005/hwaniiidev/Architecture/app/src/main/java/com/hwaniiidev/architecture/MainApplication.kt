package com.hwaniiidev.architecture

import android.app.Application
import com.hwaniiidev.architecture.module.mainViewModel
import com.hwaniiidev.architecture.module.searchModule
import com.hwaniiidev.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(mainViewModel,dataModule)
        }
    }
}