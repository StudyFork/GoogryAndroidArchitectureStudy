package com.olaf.nukeolaf

import android.app.Application
import com.olaf.nukeolaf.module.mainViewModelModule
import com.olaf.nukeolaf.module.movieModule
import com.olaf.nukeolaf.module.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(
                mainViewModelModule,
                movieModule,
                retrofitModule
            )
        }
    }
}