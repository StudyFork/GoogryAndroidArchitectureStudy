package com.example.studyfork

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }
}