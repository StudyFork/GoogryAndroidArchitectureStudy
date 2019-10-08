package com.architecture.study.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.MarketViewModel

class MarketViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MarketViewModel(CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource())) as T

}