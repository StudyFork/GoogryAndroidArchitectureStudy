package com.architecture.androidarchitecturestudy.util

import android.app.Application
import com.architecture.androidarchitecturestudy.data.local.SharedPreferenceUtil

class App : Application() {
    override fun onCreate() {
        prefs = SharedPreferenceUtil(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }
}