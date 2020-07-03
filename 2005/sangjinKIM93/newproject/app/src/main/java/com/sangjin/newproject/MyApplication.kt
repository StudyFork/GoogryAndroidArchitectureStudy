package com.sangjin.newproject

import android.app.Application
import com.example.data.di.dataModule
import com.sangjin.newproject.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(this@MyApplication)
            modules(appModule)
            modules(dataModule)
        }
    }
}