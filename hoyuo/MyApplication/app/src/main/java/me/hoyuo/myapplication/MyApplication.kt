package me.hoyuo.myapplication

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.timber.StethoTree
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        Timber.plant(Timber.DebugTree())
        Timber.plant(StethoTree())

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        )
    }
}