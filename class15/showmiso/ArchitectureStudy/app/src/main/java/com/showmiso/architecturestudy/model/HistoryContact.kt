package com.showmiso.architecturestudy.model

interface HistoryContact {
    interface View {

    }

    interface Presenter {
        fun getHistory(): List<String>?
        fun removeHistory(query: String)
    }
}