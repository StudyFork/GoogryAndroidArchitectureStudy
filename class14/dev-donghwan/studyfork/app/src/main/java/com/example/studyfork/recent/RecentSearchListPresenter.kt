package com.example.studyfork.recent

import com.example.studyfork.data.repository.Repository

class RecentSearchListPresenter(
    private val view: RecentSearchListContract.View,
    private val repository: Repository
) :
    RecentSearchListContract.Presenter {

    override fun getRecentSearchList() {
        val result = repository.getRecentSearchList()
        if (result.isEmpty()) {
            view.showListIsEmpty()
        } else {
            view.showRecentSearchList(result)
        }
    }
}