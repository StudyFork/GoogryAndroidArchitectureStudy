package com.onit.googlearchitecturestudy.di

import android.app.Application
import org.koin.core.context.startKoin

class StudyProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { modules(applicationModule) }
    }
}