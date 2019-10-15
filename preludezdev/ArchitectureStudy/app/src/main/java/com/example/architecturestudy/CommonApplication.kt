package com.example.architecturestudy

import android.app.Application
import com.example.architecturestudy.di.networkModule
import com.example.architecturestudy.di.repositoryModule
import com.example.architecturestudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CommonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CommonApplication)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}