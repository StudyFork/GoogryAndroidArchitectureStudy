package com.hhi.myapplication

import android.app.Application
import com.hhi.myapplication.data.local.SharedPreferenceUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreferenceUtil(applicationContext)
    }

    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }
}