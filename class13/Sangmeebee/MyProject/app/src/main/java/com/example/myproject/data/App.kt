package com.example.myproject.data

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(applicationContext)
    }

    companion object {
        lateinit var prefs: Preferences
    }
}
