package com.example.myproject.data

import android.app.Application

class App : Application() {

    companion object {
        lateinit var prefs: Preferences
    }

    override fun onCreate() {
        prefs = Preferences(applicationContext)
        super.onCreate()
    }
}
