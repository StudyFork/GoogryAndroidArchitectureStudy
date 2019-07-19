package com.android.studyfork.app

import android.app.Application
import timber.log.Timber

class StudyForkApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }
}