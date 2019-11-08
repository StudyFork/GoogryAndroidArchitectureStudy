package com.android.studyfork.ui.tickerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TickerViewModelFactory(private val market : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TickerViewModel(market = market) as T
    }
}