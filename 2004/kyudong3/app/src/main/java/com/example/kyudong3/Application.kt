package com.example.kyudong3

import android.app.Application
import com.example.kyudong3.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(applicationContext)
            modules(
                localDataModule,
                mapperModule,
                networkModule,
                providerModule,
                remoteDataModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}