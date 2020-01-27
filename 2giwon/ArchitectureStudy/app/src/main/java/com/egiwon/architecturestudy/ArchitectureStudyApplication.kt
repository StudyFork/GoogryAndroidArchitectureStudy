package com.egiwon.architecturestudy

import android.app.Application
import com.egiwon.architecturestudy.di.viewModule
import com.egiwon.architecturestudy.ext.setupKoin
import com.egiwon.data.di.dataModule
import com.egiwon.local.di.localDataSourceModule
import com.egiwon.remote.di.networkModule
import com.egiwon.remote.di.remoteDataSourceModule

class ArchitectureStudyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(
            this,
            viewModule,
            dataModule,
            remoteDataSourceModule,
            localDataSourceModule,
            networkModule
        )
    }
}