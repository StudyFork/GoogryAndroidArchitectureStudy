package com.architecture.study.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.TickerViewModel

class TickerViewModelFactory(private val tabList: List<String>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        TickerViewModel(
            CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()),
            tabList
        ) as T
}