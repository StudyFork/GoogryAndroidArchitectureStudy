package com.deepco.studyfork

import android.app.Application
import com.deepco.studyfork.data.local.SharedPreferenceUtil

class MyApplication : Application() {
    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }

    override fun onCreate() {
        prefs = SharedPreferenceUtil(applicationContext)
        super.onCreate()
    }
}
