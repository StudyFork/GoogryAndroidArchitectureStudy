package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract

class RecentPresenter(
    private val view: RecentContract.View
) : MovieListPresenter(view), RecentContract.Presenter {
    override suspend fun getRecentSearch() {
        val recentList = repository.searchRecent()
        view.showRecentChips(recentList)
    }

}

