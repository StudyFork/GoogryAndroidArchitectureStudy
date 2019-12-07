package com.jskim5923.architecturestudy.di.coin

import androidx.lifecycle.ViewModel
import com.jskim5923.architecturestudy.coin.CoinViewModel
import com.jskim5923.architecturestudy.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CoinViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    abstract fun bindCoinViewModel(mainViewModel: CoinViewModel): ViewModel
}