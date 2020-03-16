package com.mtjin.androidarchitecturestudy.ui.search

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieSearchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showEmptyQueryToast()
        fun showWaitToast()
        fun showNetworkErrorToast()
        fun showNetworkSuccessToast()
        fun showNoMovieToast()
        fun showLastPageToast()
        fun scrollResetState()
        fun adapterClear()
        fun adapterSetItems(movieList: List<Movie>)
    }

    interface Presenter {
        fun requestMovie(query: String)
        fun requestPagingMovie(query: String, offset: Int)
    }
}