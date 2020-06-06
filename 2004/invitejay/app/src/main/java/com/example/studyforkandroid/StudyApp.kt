package com.example.studyforkandroid

import android.app.Application
import com.example.studyforkandroid.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StudyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                repositoryModule,
                viewModelModule,
                retrofitModule,
                remoteDataModule,
                apiModule
            )
        }
    }
}