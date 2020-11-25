package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.RecentSearch

interface RecentContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter {
        suspend fun getRecentSearch(): List<RecentSearch>
    }
}