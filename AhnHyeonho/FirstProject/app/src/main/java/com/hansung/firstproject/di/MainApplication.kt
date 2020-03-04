package com.hansung.firstproject.di

import android.app.Application
import com.hansung.firstproject.BuildConfig
import com.hansung.remote.di.DataSourceModule
import com.hansung.repository.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MainApplication)
            modules(
                listOf(
                    DataSourceModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
    }
}