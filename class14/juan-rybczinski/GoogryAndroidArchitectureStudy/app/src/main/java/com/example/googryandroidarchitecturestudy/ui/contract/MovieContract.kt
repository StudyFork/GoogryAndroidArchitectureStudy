package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MovieContract {

    interface View : BaseContract.View {
        fun showMovieList(items: List<Movie>)
        fun navToRecentSearch()
    }

    interface Presenter : BaseContract.Presenter {
        suspend fun queryMovieList(query: String)
    }

}