package com.hong.architecturestudy.ui.main

interface MainContract {

    interface View {
        fun showQueryEmpty()
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun getMovieList(query: String)
    }
}