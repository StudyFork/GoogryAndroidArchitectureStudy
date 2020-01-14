package com.example.architecturestudy

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        lateinit var instance: Application
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext
}