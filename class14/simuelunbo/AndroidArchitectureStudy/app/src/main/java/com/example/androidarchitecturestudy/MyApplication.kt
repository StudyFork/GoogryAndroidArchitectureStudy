package com.example.androidarchitecturestudy

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(applicationContext)
    }

    companion object {
        lateinit var prefs: Preferences
    }
}