package com.example.studyfork.recent

import com.example.studyfork.data.repository.Repository

class RecentSearchListPresenter(
    private val view: RecentSearchListContract.View,
    private val repository: Repository
) :
    RecentSearchListContract.Presenter {

    override fun getRecentSearchList() {
        view.showRecentSearchList(repository.getRecentSearchList())
    }
}