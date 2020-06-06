package com.eunice.eunicehong

import android.app.Application
import com.eunice.eunicehong.module.movieModelModule
import com.eunice.eunicehong.module.retrofitModule
import com.eunice.eunicehong.module.viewModelDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    retrofitModule,
                    movieModelModule,
                    viewModelDependency
                )
            )
        }
    }
}