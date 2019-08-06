package com.nanamare.mac.sample

import android.app.Application
import android.content.Context
import com.nanamare.mac.sample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@AppApplication)
            modules(listOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                apiManagerModule,
                dataSourceModule))
            logger(if (BuildConfig.DEBUG) {
                AndroidLogger()
            } else {
                EmptyLogger()
            })
        }

    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: AppApplication
            private set
    }

}
