package com.jskim5923.architecturestudy.di.coin

import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.di.scope.CoinScope
import dagger.Module
import dagger.Provides

@Module
class CoinModule {
    @CoinScope
    @Provides
    fun provideCoinListAdapter() = CoinListAdapter()
}