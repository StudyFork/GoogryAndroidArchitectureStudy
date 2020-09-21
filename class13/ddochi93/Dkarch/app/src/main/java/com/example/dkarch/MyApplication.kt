package com.example.dkarch

import android.app.Application
import android.content.Context

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Context
    }
}
