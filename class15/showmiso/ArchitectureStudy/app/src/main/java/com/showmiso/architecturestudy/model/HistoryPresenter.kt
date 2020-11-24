package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.data.repository.NaverRepository

class HistoryPresenter(
    private val view: HistoryContact.View,
    private val naverRepository: NaverRepository
) : HistoryContact.Presenter {

    override fun getHistory(): List<String>? {
        return naverRepository.getHistory()
    }

    override fun removeHistory(query: String) {
        naverRepository.removeHistory(query)
    }

    override fun removeAll() {
        naverRepository.removeAll()
    }
}