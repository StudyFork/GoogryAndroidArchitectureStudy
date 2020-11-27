package com.showmiso.architecturestudy.model

interface HistoryContact {
    interface View {
        fun removeAllHistory()
    }

    interface Presenter {
        fun getHistory(): List<String>?
        fun removeHistory(query: String)
        fun removeAllHistory()
    }
}