package com.jay.architecturestudy

import android.app.Application
import com.jay.architecturestudy.di.networkModule
import com.jay.architecturestudy.di.repositoryModule
import com.jay.architecturestudy.di.storageModule
import com.jay.architecturestudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@MainApplication) // inject android context
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    storageModule,
                    networkModule
                )
            )
        }

    }

    companion object {
        lateinit var instance: MainApplication
            private set
    }
}