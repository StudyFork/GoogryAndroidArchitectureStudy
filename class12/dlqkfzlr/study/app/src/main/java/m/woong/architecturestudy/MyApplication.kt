package m.woong.architecturestudy

import android.app.Application
import m.woong.architecturestudy.di.movieModule
import m.woong.architecturestudy.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(movieModule, networkModule)

        }
    }
}