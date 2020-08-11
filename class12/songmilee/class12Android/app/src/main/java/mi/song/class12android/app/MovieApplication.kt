package mi.song.class12android.app

import android.app.Application
import mi.song.class12android.module.movieSearchModule
import mi.song.class12android.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MovieApplication)
            modules(listOf(movieSearchModule, networkModule))
        }
    }
}