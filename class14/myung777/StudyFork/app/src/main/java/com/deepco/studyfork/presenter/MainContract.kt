package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.model.Item

interface MainContract {
    interface View : BaseContract.View {
        fun setMovieList(list: List<Item>)
    }

    interface Presenter : BaseContract.Presenter {
        fun queryMovie(query: String)
        fun setRecentSearch(query: String)
    }
}