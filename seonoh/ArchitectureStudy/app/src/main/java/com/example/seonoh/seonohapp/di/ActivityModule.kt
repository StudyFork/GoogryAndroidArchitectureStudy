package com.example.seonoh.seonohapp.di

import com.example.seonoh.seonohapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [CoinFragmentModule::class]
    )
    abstract fun bindMainActivity() : MainActivity
}