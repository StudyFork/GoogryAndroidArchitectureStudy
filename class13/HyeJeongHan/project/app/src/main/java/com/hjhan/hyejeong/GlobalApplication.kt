package com.hjhan.hyejeong

import android.app.Application

class GlobalApplication : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: GlobalApplication
    }
}