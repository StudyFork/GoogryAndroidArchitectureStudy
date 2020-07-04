package com.tsdev.tsandroid

import android.app.Application
import com.tsdev.data.di.mapperModule
import com.tsdev.data.di.naverMovieRepository
import com.tsdev.presentation.di.resourceProviderModule
import com.tsdev.presentation.di.viewModelModule
import com.tsdev.remote.di.networkModule
import com.tsdev.remote.di.remoteMapperModule
import com.tsdev.remote.di.remoteModule
import com.tsdev.tsandroid.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NaverMovieSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                // koin Log 찍기.
                androidLogger()
            }
            androidContext(applicationContext)
            modules(
                networkModule,
                remoteMapperModule,
                mapperModule,
                naverMovieRepository,
                rxJavaEventBusModule,
                viewModelModule,
                remoteModule,
                resourceProviderModule
            )
        }
    }
}