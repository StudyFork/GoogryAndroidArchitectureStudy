package com.example.study

import android.app.Application
import com.example.study.di.appModule
import com.example.study.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG) androidLogger()
            androidContext(this@App)
            modules(appModule, networkModule)
        }
    }
}