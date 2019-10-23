package study.architecture.myarchitecture

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import study.architecture.myarchitecture.di.*
import timber.log.Timber

class MyArchitectureApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            logger(
                if (BuildConfig.DEBUG) {
                    AndroidLogger()
                } else {
                    EmptyLogger()
                }
            )

            androidContext(this@MyArchitectureApplication)

            modules(
                listOf(
                    localModule, networkModule, remoteModule, repositoryModule, viewModelModule
                )
            )
        }
    }
}