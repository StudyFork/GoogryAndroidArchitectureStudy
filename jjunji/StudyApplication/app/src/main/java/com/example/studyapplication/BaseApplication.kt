package com.example.studyapplication

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.studyapplication.util.MyLogger
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MyLogger.e(">>> onCreate")

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
}