package com.example.androidarchitecturestudy.ui

import com.example.androidarchitecturestudy.data.model.MovieData

interface MainContract {
    interface View : BaseContract.View {
        fun hideKeyboard()
        fun showQueryEmpty()
        fun showResult(movieData: MovieData)
        fun openMovieLink(link: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun requestMovieInfo(title: String)
        fun openMovieLink(link: String)
    }
}