package com.android.studyfork.app

import android.app.Application
import com.android.studyfork.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject


class StudyForkApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var DispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupTimber()
    }

    private fun setupDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun androidInjector(): AndroidInjector<Any> = DispatchingAndroidInjector

}