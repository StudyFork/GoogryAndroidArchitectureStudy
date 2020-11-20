package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MovieContract {

    interface View : BaseContract.View {
        fun showMovieList(items: List<Movie>)
        fun showQueryEmpty()
        fun showNoMovieSearchResult()
        fun showMovieDetail(item: Movie)
        fun showMovieSearchFailed(e: Exception)
    }

    interface Presenter : BaseContract.Presenter {
        suspend fun queryMovieList(query: String)
        fun selectMovieItem(item: Movie)
    }

}