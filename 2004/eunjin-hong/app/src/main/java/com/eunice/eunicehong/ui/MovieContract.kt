package com.eunice.eunicehong.ui

interface MovieContract {
    interface View {
        fun showRemoveHistoryConfirmDialog()
        fun showDetail()
    }

    interface Presenter {
        fun search(query: String)
        fun removeHistory()
    }
}