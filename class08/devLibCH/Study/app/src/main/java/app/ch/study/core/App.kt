package app.ch.study.core

import android.app.Application
import android.content.Context
import app.ch.study.di.apiModule
import app.ch.study.di.networkModule
import app.ch.study.di.repositoryModelModule
import app.ch.study.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(listOf(
                networkModule,
                apiModule,
                repositoryModelModule,
                viewModelModule))
        }
    }

    companion object {
        lateinit var context: Context
    }
}