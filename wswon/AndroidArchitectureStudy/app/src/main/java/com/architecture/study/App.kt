package com.architecture.study

import android.app.Application
import android.content.Context
import com.architecture.study.data.repository.di.repositoryModule
import com.architecture.study.data.source.remote.di.remoteModule
import com.architecture.study.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin{
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, remoteModule))
        }
    }

    fun context(): Context = applicationContext
}