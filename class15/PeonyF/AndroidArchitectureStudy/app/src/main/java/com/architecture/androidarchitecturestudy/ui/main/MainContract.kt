package com.architecture.androidarchitecturestudy.ui.main

import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.ui.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun removeKeyboard()
        fun updateMovieRecycler(items: List<Movie>)
        fun failMovieGet(msg: String)
        fun noQuery()
        fun noResult()
    }

    interface Presenter : BaseContract.Presenter {
        fun findMovie(query: String)
    }
}
