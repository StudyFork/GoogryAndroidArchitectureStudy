package com.hhi.myapplication

import android.app.Application
import com.hhi.myapplication.data.local.SharedPreferenceUtil

class App : Application() {
    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }

    override fun onCreate() {
        prefs = SharedPreferenceUtil(applicationContext)
        super.onCreate()
    }
}