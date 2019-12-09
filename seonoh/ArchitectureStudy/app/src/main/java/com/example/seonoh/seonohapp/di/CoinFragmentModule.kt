package com.example.seonoh.seonohapp.di

import com.example.seonoh.seonohapp.CoinFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoinFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideCoinFragment() : CoinFragment
}