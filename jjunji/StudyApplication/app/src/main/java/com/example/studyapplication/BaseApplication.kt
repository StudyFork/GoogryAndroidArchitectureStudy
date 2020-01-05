package com.example.studyapplication

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DEBUG = isDebuggable(this)
        initRealm()
    }

    private fun initRealm () {
        Realm.init(this)
        val config : RealmConfiguration = RealmConfiguration.Builder()
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun isDebuggable(context : Context) : Boolean {
        var debuggable = false

        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return debuggable
    }

    companion object {
        var DEBUG = false
    }
}