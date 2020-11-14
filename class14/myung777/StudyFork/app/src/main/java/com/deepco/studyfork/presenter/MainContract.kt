package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.model.Item

interface MainContract {
    interface View {
        fun showQueryEmpty()
        fun showMovieEmpty(error: String)
        fun setMovieList(list: List<Item>)
    }

    interface Presenter {
        fun queryMovie(query: String)
    }
}