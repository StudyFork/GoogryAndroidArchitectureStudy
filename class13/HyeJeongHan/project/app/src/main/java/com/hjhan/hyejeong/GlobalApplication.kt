package com.hjhan.hyejeong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: GlobalApplication
    }
}