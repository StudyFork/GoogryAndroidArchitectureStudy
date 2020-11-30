package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.RecentSearch

interface RecentContract {
    interface View : MovieListContract.View {
        fun showRecentChips(recentList: List<RecentSearch>)
    }

    interface Presenter : MovieListContract.Presenter {
        suspend fun getRecentSearch()
    }
}