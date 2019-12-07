package com.jskim5923.architecturestudy.di.main

import com.jskim5923.architecturestudy.coin.CoinFragment
import com.jskim5923.architecturestudy.di.scope.CoinScope
import com.jskim5923.architecturestudy.di.coin.CoinModule
import com.jskim5923.architecturestudy.di.coin.CoinViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @CoinScope
    @ContributesAndroidInjector(
        modules = [CoinModule::class, CoinViewModelModule::class]
    )
    abstract fun contributeCoinFragment(): CoinFragment
}