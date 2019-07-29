package com.aiden.aiden.architecturepatternstudy

import android.app.Application
import com.aiden.aiden.architecturepatternstudy.di.getAppModule
import com.aiden.aiden.architecturepatternstudy.di.getNetworkModule
import com.aiden.aiden.architecturepatternstudy.di.getRepositoryModule
import com.aiden.aiden.architecturepatternstudy.ext.setupKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin(
            this,
            getNetworkModule("https://api.upbit.com/"),
            getAppModule(),
            getRepositoryModule(applicationContext)
        )
    }

}