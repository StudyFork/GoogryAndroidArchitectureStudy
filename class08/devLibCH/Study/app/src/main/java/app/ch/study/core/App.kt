package app.ch.study.core

import android.app.Application
import app.ch.study.di.apiModule
import app.ch.study.di.networkModule
import app.ch.study.di.roomModule
import app.ch.study.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(apiModule)
            modules(roomModule)
            modules(networkModule)
            modules(viewModelModule)
        }
    }
}