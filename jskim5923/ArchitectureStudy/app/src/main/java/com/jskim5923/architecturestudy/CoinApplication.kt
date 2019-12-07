package com.jskim5923.architecturestudy

import com.jskim5923.architecturestudy.di.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

class CoinApplication : DaggerApplication() {
    override fun applicationInjector() =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "[${element.fileName} > ${element.methodName} > # ${element.lineNumber}]"
            }
        })

    }
}