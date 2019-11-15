package com.test.androidarchitecture

import com.test.androidarchitecture.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class UpbitApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.factory().create(applicationContext)
    }
}