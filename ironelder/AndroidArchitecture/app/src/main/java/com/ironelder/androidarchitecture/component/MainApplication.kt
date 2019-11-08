package com.ironelder.androidarchitecture.component

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialized()
    }

    fun initialized() {

    }
}