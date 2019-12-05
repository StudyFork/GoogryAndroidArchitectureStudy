package com.android.studyfork.dagger.module

import com.android.studyfork.dagger.scope.PerActivity
import com.android.studyfork.ui.main.MainActivity
import com.android.studyfork.ui.main.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [MainActivityModule::class]
    )
    abstract fun bindMainActivity() : MainActivity
}
