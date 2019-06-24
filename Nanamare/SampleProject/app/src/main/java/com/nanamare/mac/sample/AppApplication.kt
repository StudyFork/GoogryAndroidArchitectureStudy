package com.nanamare.mac.sample

import android.app.Application
import android.content.Context

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: AppApplication
            private set
    }

}
