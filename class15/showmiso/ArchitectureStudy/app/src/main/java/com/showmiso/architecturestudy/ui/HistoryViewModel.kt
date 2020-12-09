package com.showmiso.architecturestudy.ui

import androidx.databinding.ObservableField
import com.showmiso.architecturestudy.data.repository.NaverRepository

class HistoryViewModel(
    private val naverRepository: NaverRepository
) {
    val historyList = ObservableField<List<String>>()

    val showNoHistory = ObservableField<Unit>()
    val showRemoveAllHistory = ObservableField<Unit>()

    val backPressedEvent = ObservableField<Unit>()

    fun updateHistoryList() {
        val list = naverRepository.getHistory()
        list?.let {
            if (it.isEmpty()) {
                showNoHistory.notifyChange()
            } else {
                historyList.set(it)
            }
        }
    }

    fun removeHistory(query: String) {
        naverRepository.removeHistory(query)
        updateHistoryList()
    }

    fun removeAllHistory() {
        naverRepository.removeAllHistory()
        showRemoveAllHistory.notifyChange()
    }

    fun backPressed() {
        backPressedEvent.notifyChange()
    }

}