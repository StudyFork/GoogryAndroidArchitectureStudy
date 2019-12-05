package com.android.studyfork.dagger

import android.app.Application
import android.content.Context
import com.android.studyfork.app.StudyForkApplication
import com.android.studyfork.dagger.module.ActivityBuilder
import com.android.studyfork.dagger.module.DataModule
import com.android.studyfork.dagger.module.NetworkModule
import com.android.studyfork.dagger.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class,
        DataModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<StudyForkApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance

        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: Application)
}