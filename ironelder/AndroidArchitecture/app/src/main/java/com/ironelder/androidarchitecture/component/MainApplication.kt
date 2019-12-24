package com.ironelder.androidarchitecture.component

import android.app.Application
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialized()
    }

    private fun initialized() {
        SearchResultDatabase.getInstance(applicationContext)
    }
}