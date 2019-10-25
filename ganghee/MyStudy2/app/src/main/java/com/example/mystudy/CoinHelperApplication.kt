package com.example.mystudy

import android.app.Application
import com.example.mystudy.di.dataSourceModule
import com.example.mystudy.di.getRemoteServiceModules
import com.example.mystudy.di.repositoryModule
import com.example.mystudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinHelperApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinHelperApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule,
                    getRemoteServiceModules("https://api.upbit.com/v1/")
                )
            )
        }
    }
}