package com.example.seonoh.seonohapp

import android.app.Application
import com.example.seonoh.seonohapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SeonohApplication : Application(),HasAndroidInjector {

    @Inject
    lateinit var DispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }
//
    private fun setupDagger() {

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = DispatchingAndroidInjector



}
