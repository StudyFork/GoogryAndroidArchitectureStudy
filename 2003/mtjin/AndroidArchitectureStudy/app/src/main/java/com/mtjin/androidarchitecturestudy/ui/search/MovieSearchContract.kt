package com.mtjin.androidarchitecturestudy.ui.search

import com.mtjin.androidarchitecturestudy.data.movie_search.Movie

interface MovieSearchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showEmptyQuery()
        fun showWait()
        fun showNetworkError()
        fun showNetworkSuccess()
        fun showNoMovie()
        fun showLastPage()
        fun scrollResetState()
        fun adapterClear()
        fun adapterSetItems(movieList: List<Movie>)
    }

    interface Presenter {
        fun requestMovie(query: String)
        fun requestPagingMovie(query: String, offset: Int)
    }
}