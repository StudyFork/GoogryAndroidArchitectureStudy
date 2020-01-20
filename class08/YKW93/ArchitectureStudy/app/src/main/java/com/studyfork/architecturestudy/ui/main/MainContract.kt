package com.studyfork.architecturestudy.ui.main

import com.studyfork.architecturestudy.data.model.MovieResponse

interface MainContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMovieList(items: MovieResponse)
        fun showErrorEmptyQuery()
        fun showErrorEmptyResult()
    }

    interface Presenter {
        fun getMovieList(query: String)
        fun onViewDetached()
    }
}