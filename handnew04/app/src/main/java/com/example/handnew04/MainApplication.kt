package com.example.handnew04

import android.app.Application
import com.example.handnew04.di.localDataModule
import com.example.handnew04.di.networkManagerModule
import com.example.handnew04.di.remoteDataModule
import com.example.handnew04.di.viewModelDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    localDataModule,
                    remoteDataModule,
                    networkManagerModule,
                    viewModelDataModule
                )
            )
        }
    }
}