package r.test.rapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import r.test.data.dataModule

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidLogger()
            // declare used Android context
            androidContext(this@KoinApplication)
            // declare modules
            modules(dataModule)
        }
    }
}