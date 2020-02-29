package com.jay.architecturestudy

import android.app.Application
import com.jay.architecturestudy.di.viewModelModule
import com.jay.local.di.localModule
import com.jay.local.di.storageModule
import com.jay.remote.di.networkModule
import com.jay.remote.di.remoteModule
import com.jay.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication) // inject android context
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    remoteModule,
                    networkModule,
                    localModule,
                    storageModule
                )
            )
        }

    }
    
}