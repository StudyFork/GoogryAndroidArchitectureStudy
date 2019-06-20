package com.namjackson.archstudy.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namjackson.archstudy.data.source.TickerRepository
import com.namjackson.archstudy.view.coinlist.CoinListViewModel

class ViewModelFactory private constructor(
    private val tickerRepository: TickerRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CoinListViewModel::class.java) ->
                    CoinListViewModel(tickerRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(
            tickerRepository: TickerRepository
        ): ViewModelFactory {
            if (!this::instance.isInitialized) {
                instance =
                    ViewModelFactory(tickerRepository)
            }
            return instance
        }

    }

}