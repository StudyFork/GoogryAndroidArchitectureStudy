package kr.dktsudgg.androidarchitecturestudy

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import kr.dktsudgg.androidarchitecturestudy.data.datasource.local.SharedPreferencesTool

@HiltAndroidApp
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        SharedPreferencesTool.init(this)

    }
}