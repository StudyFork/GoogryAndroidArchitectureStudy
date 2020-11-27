package com.example.hw2_project

import android.app.Application

class App : Application() {
    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }

    override fun onCreate() {
        prefs = SharedPreferenceUtil(applicationContext)
        super.onCreate()
    }
}