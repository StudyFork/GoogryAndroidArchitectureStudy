package com.example.googryandroidarchitecturestudy.ui.presenter

import android.content.Context
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract

class RecentPresenter(
    private val view: RecentContract.View,
    context: Context
) : MovieListPresenter(view, context), RecentContract.Presenter {
    override suspend fun getRecentSearch() {
        val recentList = repository.searchRecent()
        view.showRecentChips(recentList)
    }

}

