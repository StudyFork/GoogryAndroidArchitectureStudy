package com.egiwon.architecturestudy

import android.app.Application
import com.egiwon.architecturestudy.di.dataSourceModule
import com.egiwon.architecturestudy.di.networkModule
import com.egiwon.architecturestudy.di.viewModule
import com.egiwon.architecturestudy.ext.setupKoin

class ArchitectureStudyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(this, viewModule, dataSourceModule, networkModule)
    }
}