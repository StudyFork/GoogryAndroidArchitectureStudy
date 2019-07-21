package study.architecture.myarchitecture

import android.app.Application
import timber.log.Timber

class MyArchitectureApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


}