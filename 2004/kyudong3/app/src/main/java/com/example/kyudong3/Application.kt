package com.example.kyudong3

import android.app.Application
import com.example.kyudong3.di.providerModule
import com.example.kyudong3.di.viewModelModule
import com.kyudong.data.di.repositoryModule
import com.kyudong.local.di.localDataModule
import com.kyudong.local.di.localMapperModule
import com.kyudong.remote.di.networkModule
import com.kyudong.remote.di.remoteDataModule
import com.kyudong.remote.di.remoteMapperModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(applicationContext)
            modules(
                localDataModule,
                localMapperModule,
                remoteMapperModule,
                networkModule,
                providerModule,
                remoteDataModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}