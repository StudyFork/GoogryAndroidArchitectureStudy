package com.siwon.prj

import android.app.Application
import com.siwon.prj.di.remotemodule
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(remotemodule)
        }
    }
}