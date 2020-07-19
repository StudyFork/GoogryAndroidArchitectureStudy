package com.hyper.hyapplication.ui

import com.hyper.hyapplication.model.ResultGetSearchMovie

interface MainContract {

    interface View {
        fun showMovie(item: List<ResultGetSearchMovie.Item>)
        fun showFailure(it: Throwable)
        fun showEmptyMessage()
        fun searchMovie(movieName: String)
    }

    interface Presenter {
        fun movieSearch(query: String)
    }
}