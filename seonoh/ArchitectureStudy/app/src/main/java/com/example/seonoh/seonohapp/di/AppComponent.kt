package com.example.seonoh.seonohapp.di

import android.app.Application
import com.example.seonoh.seonohapp.SeonohApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        CoinFragmentModule::class,
        ActivityModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)

interface AppComponent : AndroidInjector<SeonohApplication> {
        @Component.Builder
        interface Builder {
            @BindsInstance

            fun application(application: Application): Builder

            fun build(): AppComponent
        }
}