package com.hhi.myapplication.recentSearch

import com.hhi.myapplication.base.BaseContract

interface RecentSearchContract {
    interface View : BaseContract.View {
        fun showQueryList(list: List<String>)
    }

    interface Presenter : BaseContract.Presenter {
        fun searchRecentQuery()
    }
}