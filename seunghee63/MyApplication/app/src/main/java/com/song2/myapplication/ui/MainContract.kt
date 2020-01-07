package com.song2.myapplication.ui

interface MainContract {
    interface View {
        fun shoeGetMovieSuccess()
        fun showMovieNotExist()
        fun showGetMoreData()
    }

    interface Presenter {
        fun getMovie(keyword : String)
    }
}