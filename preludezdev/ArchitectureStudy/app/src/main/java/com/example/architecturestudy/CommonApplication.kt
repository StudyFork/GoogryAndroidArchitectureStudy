package com.example.architecturestudy

import android.app.Application
import org.koin.core.context.startKoin

class CommonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

        }
    }

}