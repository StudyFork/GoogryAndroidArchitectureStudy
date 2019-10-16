package kr.schoolsharing.coinhelper

import android.app.Application
import kr.schoolsharing.coinhelper.data.di.repositoryModule
import kr.schoolsharing.coinhelper.data.remote.di.remoteModule
import kr.schoolsharing.coinhelper.di.getNetworkModule
import kr.schoolsharing.coinhelper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UpbitApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UpbitApplication)
            modules(
                listOf(
                    getNetworkModule("https://api.upbit.com/"),
                    viewModelModule,
                    repositoryModule,
                    remoteModule
                )
            )
        }
    }
}