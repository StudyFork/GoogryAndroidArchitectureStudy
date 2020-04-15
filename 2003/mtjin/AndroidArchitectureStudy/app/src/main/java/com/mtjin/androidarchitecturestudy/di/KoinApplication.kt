package com.mtjin.androidarchitecturestudy.di

import android.app.Application
import com.mtjin.androidarchitecturestudy.BuildConfig
import com.mtjin.androidarchitecturestudy.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            else androidLogger(Level.NONE)
            androidContext(this@KoinApplication)
            modules(
                repositoryModule,
                localDataModule,
                remoteDataModule,
                networkModule,
                viewModelModule,
                apiModule
            )

        }
    }
}