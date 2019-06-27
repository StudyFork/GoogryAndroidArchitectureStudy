package com.namjackson.archstudy.base

import android.app.Application
import com.namjackson.archstudy.di.dataSourceModule
import com.namjackson.archstudy.di.networkModule
import com.namjackson.archstudy.di.repositoryModule
import com.namjackson.archstudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {

            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(listOf(
                viewModelModule,
                repositoryModule,
                dataSourceModule,
                networkModule
            ))
        }
    }
}