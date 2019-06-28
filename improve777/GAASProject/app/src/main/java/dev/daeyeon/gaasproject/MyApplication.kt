package dev.daeyeon.gaasproject

import android.app.Application
import dev.daeyeon.gaasproject.module.networkModule
import dev.daeyeon.gaasproject.module.repositoryModule
import dev.daeyeon.gaasproject.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}