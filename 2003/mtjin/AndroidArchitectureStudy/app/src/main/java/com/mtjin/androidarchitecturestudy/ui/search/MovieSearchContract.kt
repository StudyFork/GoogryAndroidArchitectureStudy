package com.mtjin.androidarchitecturestudy.ui.search

interface MovieSearchContract {

    interface View {
        fun showToast(msg: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun requestMovie(query: String)
        fun requestPagingMovie(query: String, offset: Int)
    }
}