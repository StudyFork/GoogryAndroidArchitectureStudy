package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.data.model.MovieItem

interface MovieContract {

    interface View {
        fun showErrorMessage(message : String)
        fun showResult(item: List<MovieItem>)
        fun showEmpty(message: String)
    }

    interface Presenter {
        fun taskSearch(isNetwork: Boolean, keyword : String)
        fun getLastData()
    }
}