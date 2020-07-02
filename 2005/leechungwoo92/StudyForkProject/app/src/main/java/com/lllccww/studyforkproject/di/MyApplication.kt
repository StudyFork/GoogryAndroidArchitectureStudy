package com.lllccww.studyforkproject.di

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.lllccww.data.di.repositoryModule
import com.lllccww.remote.di.remoteDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@MyApplication)
            modules(
                //movieModule,
                repositoryModule,
                remoteDataModule,
                viewmodelModule
            )

        }
    }
}