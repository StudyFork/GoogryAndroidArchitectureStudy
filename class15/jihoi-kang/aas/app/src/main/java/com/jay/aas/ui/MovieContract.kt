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
        suspend fun getMovies()
        suspend fun searchMovies(query: String)
    }

}