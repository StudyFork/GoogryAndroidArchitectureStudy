package com.showmiso.architecturestudy.model

interface HistoryContact {
    interface View {
        fun showNoHistory()
        fun updateHistoryList(list: List<String>?)
        fun showRemoveAllHistory()
    }

    interface Presenter {
        fun initSearchHistory()
        fun removeHistory(query: String)
        fun removeAllHistory()
    }
}