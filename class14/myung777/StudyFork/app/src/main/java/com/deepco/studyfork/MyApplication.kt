package com.deepco.studyfork

import android.app.Application
import com.deepco.studyfork.data.local.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreferenceUtil(applicationContext)
    }

    companion object {
        lateinit var prefs: SharedPreferenceUtil
    }
}
