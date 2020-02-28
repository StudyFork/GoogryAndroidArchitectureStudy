package com.example.handnew04

import android.app.Application
import com.example.handnew04.di.mainViewModelModule
import com.example.model.di.localDataModule
import com.example.model.di.movieRepositoryModule
import com.example.model.di.networkManagerModule
import com.example.model.di.remoteDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication.applicationContext)
            modules(
                listOf(
                    localDataModule,
                    remoteDataModule,
                    networkManagerModule,
                    mainViewModelModule,
                    movieRepositoryModule
                )
            )
        }
    }
}