package com.test.androidarchitecture.di

import android.content.Context
import com.test.androidarchitecture.UpbitApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        MarketModule::class,
        NetworkModule::class,
        TickerModule::class
    ]
)
interface AppComponent : AndroidInjector<UpbitApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}