package com.test.androidarchitecture.di

import androidx.lifecycle.ViewModel
import com.test.androidarchitecture.ui.ticker.TickerFragment
import com.test.androidarchitecture.ui.ticker.TickerViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap



@Module
abstract class TickerModule{

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun tickerFragment(): TickerFragment

    @Binds
    @IntoMap
    @ViewModelKey(TickerViewModel::class)
    internal abstract fun bindViewModel(vm: TickerViewModel): ViewModel
}