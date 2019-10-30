package study.architecture.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import study.architecture.data.datasource.local.di.localModule
import study.architecture.data.datasource.remote.di.remoteModule
import study.architecture.data.di.repositoryModule
import study.architecture.di.viewModelModule

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(remoteModule, localModule, repositoryModule, viewModelModule))
        }

    }
}