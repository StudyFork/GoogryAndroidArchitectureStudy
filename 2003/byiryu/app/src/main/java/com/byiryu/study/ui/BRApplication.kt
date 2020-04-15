package com.byiryu.study.ui

import android.app.Application
import com.byiryu.study.di.localModule
import com.byiryu.study.di.remoteModule
import com.byiryu.study.di.repositoryModule
import com.byiryu.study.di.viewModelModule
import com.byiryu.study.model.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BRApplication : Application() {

    lateinit var repository: Repository

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