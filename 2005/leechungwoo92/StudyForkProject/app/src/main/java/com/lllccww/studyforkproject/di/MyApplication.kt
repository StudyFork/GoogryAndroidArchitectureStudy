package com.lllccww.studyforkproject.di

import android.app.Application
import com.lllccww.studyforkproject.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@MyApplication)
            modules(
                movieModule,
                viewmodelModule
            )

        }
    }
}