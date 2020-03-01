package com.example.myapplication.data.di

import android.app.Application
import com.example.myapplication.data.di.module.localModule
import com.example.myapplication.data.di.module.remoteModule
import com.example.myapplication.data.di.module.repositoryModule
import com.example.myapplication.data.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    localModule,
                    remoteModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}