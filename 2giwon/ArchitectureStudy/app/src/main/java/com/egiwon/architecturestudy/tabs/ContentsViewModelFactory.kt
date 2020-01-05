package com.egiwon.architecturestudy.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.data.NaverDataRepository

class ContentsViewModelFactory(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContentsViewModel(tab, naverDataRepository) as T
    }
}