package com.android.studyfork.ui.tickerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.studyfork.repository.UpbitRepositoryImpl
import javax.inject.Inject

class TickerViewModelFactory @Inject constructor(private val repository: UpbitRepositoryImpl, private val market : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TickerViewModel(repository = repository ,market = market) as T
    }
}