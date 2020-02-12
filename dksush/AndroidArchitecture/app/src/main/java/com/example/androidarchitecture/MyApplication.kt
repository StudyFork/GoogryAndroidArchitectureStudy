package com.example.androidarchitecture

import android.app.Application
import com.example.androidarchitecture.di.dataSoureceModule
import com.example.androidarchitecture.di.repositoryModule
import com.example.androidarchitecture.di.storageMoudule
import com.example.androidarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication) // inject android context
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataSoureceModule,
                    storageMoudule
                )
            )
        }


    }
}