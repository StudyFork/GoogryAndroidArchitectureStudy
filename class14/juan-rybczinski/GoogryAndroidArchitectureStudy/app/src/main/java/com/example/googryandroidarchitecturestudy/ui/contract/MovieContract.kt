package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MovieContract {

    interface View {
        fun showMovieList(items: List<Movie>)
        fun showQueryEmpty()
        fun showNoMovieSearchResult()
        fun showMovieDetail(item: Movie)
        fun showMovieSearchFailed(e: Exception)
        fun hideKeyboard()
    }

    interface Presenter {
        suspend fun queryMovieList(query: String)
        fun selectMovieItem(item: Movie)
    }

}