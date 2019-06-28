package ado.sabgil.studyproject

import ado.sabgil.studyproject.di.dataSourceModule
import ado.sabgil.studyproject.di.networkModule
import ado.sabgil.studyproject.di.repositoryModule
import ado.sabgil.studyproject.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoinApplication)
            modules(listOf(dataSourceModule, networkModule, repositoryModule, viewModelModule))
        }
    }
}