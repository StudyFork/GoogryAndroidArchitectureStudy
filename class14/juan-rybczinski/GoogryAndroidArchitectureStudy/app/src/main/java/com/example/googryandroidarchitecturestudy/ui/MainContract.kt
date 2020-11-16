package com.example.googryandroidarchitecturestudy.ui

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MainContract {

    interface View {
        fun showMovieList(items: List<Movie>)
        fun showQueryEmpty()
        fun showNoMovieSearchResult()
        fun showMovieDetail(item: Movie)
        fun showMovieSearchFailed()
        fun hideKeyboard()
    }

    interface Presenter {
        fun queryMovieList(query: String)
        fun selectMovieItem(item: Movie)
    }

}