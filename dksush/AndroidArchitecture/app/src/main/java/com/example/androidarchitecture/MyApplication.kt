package com.example.androidarchitecture

import android.app.Application
import android.util.Log
import com.example.androidarchitecture.di.repositoryModule
import com.example.androidarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            Log.v("dksush", "여기를 타는가")

            androidContext(this@MyApplication) // inject android context
            Log.v("dksush", "여기를 타는가222")
            modules(listOf(viewModelModule, repositoryModule))  // use modules
        }




    }
}