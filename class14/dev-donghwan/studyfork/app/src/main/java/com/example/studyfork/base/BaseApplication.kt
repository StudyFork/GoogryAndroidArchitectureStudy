package com.example.studyfork.base

import android.app.Application
import android.content.SharedPreferences

class BaseApplication : Application() {

    companion object {
        fun getPref(): SharedPreferences {
            return BaseApplication().getSharedPreferences("local", MODE_PRIVATE)
        }
    }
}