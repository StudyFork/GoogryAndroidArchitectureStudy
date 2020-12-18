package com.example.hw2_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    init {
        appInstance = this
    }

    companion object {
        lateinit var appInstance: Application
    }

}