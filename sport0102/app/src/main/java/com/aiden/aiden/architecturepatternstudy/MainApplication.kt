package com.aiden.aiden.architecturepatternstudy

import android.app.Application
import com.aiden.aiden.architecturepatternstudy.di.getNetworkModule
import com.aiden.aiden.architecturepatternstudy.ext.setupKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin(
            this,
            getNetworkModule("https://api.upbit.com/")
        )
    }

}