package com.example.architecture_project.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(vmAppModule, remoteDataAppModule, localDataAppModule, repositoryAppModule))
        }
    }
}