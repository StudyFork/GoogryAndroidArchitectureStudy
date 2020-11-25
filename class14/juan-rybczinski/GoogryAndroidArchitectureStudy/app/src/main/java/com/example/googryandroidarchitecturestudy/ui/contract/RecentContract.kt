package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.RecentSearch

interface RecentContract {
    interface View : BaseContract.View {
        fun showRecentChips(recentList: List<RecentSearch>)
    }

    interface Presenter : BaseContract.Presenter {
        suspend fun getRecentSearch()
    }
}