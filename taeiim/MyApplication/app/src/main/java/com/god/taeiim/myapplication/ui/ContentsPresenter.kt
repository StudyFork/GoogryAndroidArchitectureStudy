package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverRepository

class ContentsPresenter(
    private val naverRepository: NaverRepository,
    private val view: ContentsContract.View
) : ContentsContract.Presenter {
    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun searchContents(searchType: String, query: String) {
        if (query.isBlank()) {
            view.blankSearchQuery()

        } else {
            naverRepository.getResultData(
                searchType,
                query,
                success = {
                    view.updateItems(it.items)
                    naverRepository.saveSearchResult(SearchHistory(it.items, searchType, query))
                },
                fail = { view.failToSearch() }
            )
        }
    }

}