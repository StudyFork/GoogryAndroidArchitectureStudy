package com.egiwon.architecturestudy.ui.tabs.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.ui.Tab

class HistoryViewModelFactory(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryViewModel(tab, naverDataRepository) as T
    }
}