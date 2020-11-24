package com.showmiso.architecturestudy.model

interface HistoryContact {
    interface View {
        fun removeAll()
    }

    interface Presenter {
        fun getHistory(): List<String>?
        fun removeHistory(query: String)
        fun removeAll()
    }
}