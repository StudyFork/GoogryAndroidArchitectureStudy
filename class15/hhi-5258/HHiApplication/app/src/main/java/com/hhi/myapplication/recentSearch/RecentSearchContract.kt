package com.hhi.myapplication.recentSearch

import com.hhi.myapplication.base.BaseContract

interface RecentSearchContract {
    interface View: BaseContract.View {
        fun showRecentQueryList()
    }

    interface Presenter: BaseContract.Presenter {

    }
}