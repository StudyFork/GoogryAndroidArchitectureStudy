package com.example.myproject.data

import android.app.Application

class App : Application() {

    override fun onCreate() {
        prefs = Preferences(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var prefs: Preferences
    }
}
