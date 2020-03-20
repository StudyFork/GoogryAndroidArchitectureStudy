package com.mtjin.androidarchitecturestudy.ui.search

import com.mtjin.androidarchitecturestudy.data.search.Movie

interface MovieSearchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showToast(msg: String)
        fun showEmptyQuery()
        fun showWait()
        fun showNetworkError()
        fun showNoMovie()
        fun showLastPage()
        fun scrollResetState()
        fun searchMovieSuccess(movieList: List<Movie>)
        fun pagingMovieSuccess(movieList: List<Movie>)
    }

    interface Presenter {
        fun requestMovie(query: String)
        fun requestPagingMovie(query: String, offset: Int)
    }
}