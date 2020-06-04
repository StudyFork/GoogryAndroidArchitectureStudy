package com.eunice.eunicehong

import android.app.Application
import com.eunice.eunicehong.module.appModule
import com.eunice.eunicehong.module.viewModelDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(appModule, viewModelDependency))
        }
    }
}