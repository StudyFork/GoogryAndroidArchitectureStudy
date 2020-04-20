package com.eunice.eunicehong.ui

interface MovieContract {
    interface View {
        fun showRemoveHistoryConfirmDialog()
    }

    interface Presenter {
        fun showDetail()
        fun search(query: String)
        fun removeHistory()
    }
}