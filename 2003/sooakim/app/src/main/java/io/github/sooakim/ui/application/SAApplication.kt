package io.github.sooakim.ui.application

import android.app.Application
import io.github.sooakim.BuildConfig
import io.github.sooakim.di.localModule
import io.github.sooakim.di.providerModule
import io.github.sooakim.di.remoteModule
import io.github.sooakim.di.repositoryModule
import io.github.sooakim.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SAApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@SAApplication)
            modules(
                localModule,
                remoteModule,
                repositoryModule,
                viewModelModule,
                providerModule
            )
        }
    }
}