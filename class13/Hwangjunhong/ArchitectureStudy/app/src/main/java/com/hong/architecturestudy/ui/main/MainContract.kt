package com.hong.architecturestudy.ui.main

import com.hong.architecturestudy.data.model.MovieData

interface MainContract {

    interface View {
        fun showQueryEmpty()
        fun showError(throwable: Throwable)
        fun showMovieList(movieData: List<MovieData>)
    }

    interface Presenter {
        fun getMovieList(query: String)
    }
}