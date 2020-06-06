package com.tsdev.tsandroid

import android.app.Application
import com.tsdev.tsandroid.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NaverMovieSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                // koin Log 찍기.
                androidLogger()
            }
            androidContext(applicationContext)
            modules(
                mapConverterModule,
                naverRepositoryModule,
                rxJavaEventBusModule,
                viewModelModule,
                remoteModule,
                networkModule,
                resourceProviderModule
            )
        }
    }
}