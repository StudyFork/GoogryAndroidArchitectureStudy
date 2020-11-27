package com.architecture.androidarchitecturestudy.util

import android.app.Application
import com.architecture.androidarchitecturestudy.data.local.SharedPreferenceUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreferenceUtil(applicationContext)
    }

    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }
}