package com.olaf.nukeolaf.ui

import com.olaf.nukeolaf.data.model.MovieItem

interface MainContract {
    interface View {
        fun showMovies(movies: List<MovieItem>)
        fun showEmptySearchWord()
        fun showNoResultForSearchWord(query: String)
        fun showServerError()
        fun showNetworkError()
    }

    interface Presenter {
        fun loadMovies()
        fun searchMovie(query: String?)
    }
}