package com.project.architecturestudy.di

import android.app.Application
import com.project.architecturestudy.di.modules.apiModule
import com.project.architecturestudy.di.modules.networkModule
import com.project.architecturestudy.di.modules.roomModule
import com.project.architecturestudy.di.modules.viewModelModule
import com.project.architecturestudy.extensions.setUpKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setUpKoin(
            this@App,
            apiModule,
            networkModule,
            roomModule,
            viewModelModule
        )
    }
}