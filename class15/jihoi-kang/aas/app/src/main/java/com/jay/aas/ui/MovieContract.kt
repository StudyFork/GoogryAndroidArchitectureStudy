package com.jay.aas.ui

import com.jay.aas.model.Movie

interface MovieContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun hideKeyboard()
        fun showSearchFailed()
        fun showNoResult()
        fun showMovieItems(movies: List<Movie>)
    }

    interface Presenter {
        fun getMovies()
        fun searchMovies(query: String)
    }

}