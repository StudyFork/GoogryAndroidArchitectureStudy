package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.data.repository.NaverRepository

class HistoryPresenter(
    private val view: HistoryContact.View,
    private val naverRepository: NaverRepository
) : HistoryContact.Presenter {

    override fun initSearchHistory() {
        val list = naverRepository.getHistory()
        list?.let {
            if (it.isEmpty()) {
                view.showNoHistory()
            } else {
                view.updateHistoryList(list)
            }
        }
    }

    override fun removeHistory(query: String) {
        naverRepository.removeHistory(query)
    }

    override fun removeAllHistory() {
        naverRepository.removeAllHistory()
        view.showRemoveAllHistory()
    }
}