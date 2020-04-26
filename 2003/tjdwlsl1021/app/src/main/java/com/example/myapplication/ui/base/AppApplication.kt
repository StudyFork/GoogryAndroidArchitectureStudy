package com.example.myapplication.ui.base

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.example.data2.di.repositoryModule
import com.example.local.di.localDataModule
import com.example.myapplication.di.viewModelModule
import com.example.remote.di.remoteDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            else androidLogger(Level.NONE)

            androidContext(this@AppApplication)

            modules(
                localDataModule,
                remoteDataModule,
                repositoryModule,
                viewModelModule
            )

        }
    }
}