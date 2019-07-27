package com.architecturestudy.common

import android.app.Application
import com.architecturestudy.di.dataSourceModules
import com.architecturestudy.di.getLocalServiceModules
import com.architecturestudy.di.getRemoteServiceModules
import com.architecturestudy.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    getLocalServiceModules(this@Application),
                    getRemoteServiceModules("https://api.upbit.com/"),
                    dataSourceModules,
                    viewModelModules
                )
            )
        }
    }
}