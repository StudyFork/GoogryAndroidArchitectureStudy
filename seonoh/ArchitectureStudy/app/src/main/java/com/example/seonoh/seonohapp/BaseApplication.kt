package com.example.seonoh.seonohapp

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    companion object {
        var context: Context? = null
    }
}