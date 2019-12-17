package com.test.androidarchitecture.di

import androidx.lifecycle.ViewModel
import com.test.androidarchitecture.ui.market.MarketActivity
import com.test.androidarchitecture.ui.market.MarketViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap



@Module
abstract class MarketModule{

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun marketAcitivity(): MarketActivity

    @Binds
    @IntoMap
    @ViewModelKey(MarketViewModel::class)
    internal abstract fun bindViewModel(vm: MarketViewModel): ViewModel
}