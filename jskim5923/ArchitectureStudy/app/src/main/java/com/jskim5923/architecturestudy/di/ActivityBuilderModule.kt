package com.jskim5923.architecturestudy.di

import com.jskim5923.architecturestudy.di.main.FragmentBuilderModule
import com.jskim5923.architecturestudy.di.main.MainViewModelModule
import com.jskim5923.architecturestudy.di.scope.ActivityScope
import com.jskim5923.architecturestudy.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainViewModelModule::class, FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}