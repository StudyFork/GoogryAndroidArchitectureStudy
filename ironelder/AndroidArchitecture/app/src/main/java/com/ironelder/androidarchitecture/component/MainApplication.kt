package com.ironelder.androidarchitecture.component

import android.app.Application
import android.content.res.Configuration

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialized()
    }

    fun initialized() {

    }
}