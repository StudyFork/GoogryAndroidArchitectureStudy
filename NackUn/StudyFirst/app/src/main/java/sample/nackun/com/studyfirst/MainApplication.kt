package sample.nackun.com.studyfirst

import android.app.Application
import org.koin.core.context.startKoin
import sample.nackun.com.studyfirst.di.getNetworkModule

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(getNetworkModule("https://api.upbit.com/"))
        }
    }
}