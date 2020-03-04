package io.github.sooakim.ui.application

import android.app.Application
import io.github.sooakim.pref.SAPreferencesHelper
import io.github.sooakim.pref.SAPreferencesHelperImpl

class SAApplication : Application() {
    lateinit var preferencesHelper: SAPreferencesHelper

    override fun onCreate() {
        super.onCreate()
        preferencesHelper = SAPreferencesHelperImpl(applicationContext)
    }
}