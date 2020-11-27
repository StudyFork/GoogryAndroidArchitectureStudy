package com.example.hw2_project

import android.app.Application

class App : Application() {
    override fun onCreate() {
        prefs = SharedPreferenceUtil(applicationContext)
        super.onCreate()
    }
    
    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }

}