package com.byiryu.study.ui

import android.app.Application
import com.byiryu.data.di.repositoryModule
import com.byiryu.local.di.localModule
import com.byiryu.remote.di.remoteModule
import com.byiryu.study.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BRApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BRApplication)
            modules(
                localModule,
                remoteModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}