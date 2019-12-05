package com.android.studyfork.ui.tickerlist.di

import com.android.studyfork.dagger.scope.PerFragment
import com.android.studyfork.ui.TickerListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TickerListFragmentModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun provieTickerListFragment() : TickerListFragment

}