package com.studyfork.architecturestudy.base

import android.app.Application
import android.content.Context
import com.studyfork.architecturestudy.BuildConfig
import com.studyfork.architecturestudy.di.remoteModule
import com.studyfork.architecturestudy.di.repositoryModule
import com.studyfork.architecturestudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@BaseApplication)
            modules(repositoryModule, remoteModule, viewModelModule)
        }
    }

    companion object {
        private var instance: BaseApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}