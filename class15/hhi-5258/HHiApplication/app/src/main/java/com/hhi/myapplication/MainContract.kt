package com.hhi.myapplication

import com.hhi.myapplication.data.model.MovieData

interface MainContract {
    interface View {
        fun showMovies(items: ArrayList<MovieData.MovieItem>)
        fun showEmptyQuery()
        fun showProgressBar()
        fun hideProgressBar()
        fun hideKeyboard()
    }

    interface Presenter {
        fun searchMovie(query: String)
    }
}