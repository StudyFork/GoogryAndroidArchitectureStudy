package com.architecture.androidarchitecturestudy.ui.main

import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.ui.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun removeKeyboard()
        fun updateMovieRecycler(items: List<Movie>)
        fun failMovieGet(msg: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun findMovie(keyword: String)
        fun setSearchHistory(keyword: String)
    }
}
