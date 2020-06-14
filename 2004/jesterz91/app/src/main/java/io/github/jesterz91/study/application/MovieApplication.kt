package io.github.jesterz91.study.application

import android.app.Application
import io.github.jesterz91.data.di.repositoryModule
import io.github.jesterz91.domain.di.domainModule
import io.github.jesterz91.local.di.localModule
import io.github.jesterz91.remote.di.remoteModule
import io.github.jesterz91.study.BuildConfig
import io.github.jesterz91.study.di.viewModelModule
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
            modules(
                viewModelModule,
                domainModule,
                repositoryModule,
                localModule,
                remoteModule
            )
        }
    }
}