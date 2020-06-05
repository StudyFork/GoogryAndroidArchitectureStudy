package io.github.jesterz91.study.presentation.application

import android.app.Application
import io.github.jesterz91.study.BuildConfig
import io.github.jesterz91.study.data.di.localModule
import io.github.jesterz91.study.data.di.remoteModule
import io.github.jesterz91.study.domain.di.repositoryModule
import io.github.jesterz91.study.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@MovieApplication)
            modules(localModule, remoteModule, repositoryModule, viewModelModule)
        }
    }
}