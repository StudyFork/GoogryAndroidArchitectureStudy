package com.project.architecturestudy.di

import android.app.Application
import com.project.architecturestudy.di.modules.*
import com.project.architecturestudy.extensions.setUpKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setUpKoin(
            this@App,
            viewModelModule,
            localDataSourceModule,
            remoteDataSourceModule,
            networkModule
        )
    }
}