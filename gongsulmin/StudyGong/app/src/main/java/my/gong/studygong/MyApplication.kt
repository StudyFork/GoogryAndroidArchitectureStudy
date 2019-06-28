package my.gong.studygong

import android.app.Application
import my.gong.studygong.di.repositoryModule
import my.gong.studygong.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(
                listOf(
                    repositoryModule ,
                    viewModelModule
                )
            )
        }
    }
}