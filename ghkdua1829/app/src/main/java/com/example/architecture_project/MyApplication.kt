package com.example.architecture_project

import android.app.Application
import com.example.architecture_project.di.localDataAppModule
import com.example.architecture_project.di.remoteDataAppModule
import com.example.architecture_project.di.repositoryAppModule
import com.example.architecture_project.di.vmAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    vmAppModule,
                    remoteDataAppModule,
                    localDataAppModule,
                    repositoryAppModule
                )
            )
        }
    }
}