package com.architecture.androidarchitecturestudy.ui

import com.architecture.androidarchitecturestudy.data.model.Movie

interface MainContract {
    interface View {
        fun removeKeyboard(): Boolean
        fun updateMovieRecycler(items: List<Movie>)
        fun failMovieGet(msg: String)
        fun findMovie(query: String)
        fun queryNone()
        fun resultNone()
    }

    interface Presenter {
        fun findMovie(query: String)
    }
}
