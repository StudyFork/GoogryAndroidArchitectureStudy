package com.example.study

import android.app.Application
import com.example.study.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(
                localModule,
                remoteModule,
                repositoryModule,
                viewmodelModule
            )

            )
        }
    }

}