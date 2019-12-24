package com.egiwon.architecturestudy.tabs.bottomsheet

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseContract

interface HistoryContract : BaseContract {
    interface View : BaseContract.View {
        fun showSearchQueryHistory(history: List<String>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getSearchQueryHistory(tab: Tab)
    }
}