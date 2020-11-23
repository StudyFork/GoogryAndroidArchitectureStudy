package com.hhi.myapplication.main

import com.hhi.myapplication.base.BaseContract
import com.hhi.myapplication.data.model.MovieData

interface MainContract {
    interface View : BaseContract.View {
        fun showMovies(items: ArrayList<MovieData.MovieItem>)
        fun showEmptyQuery()
        fun showProgressBar()
        fun hideProgressBar()
        fun hideKeyboard()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchMovie(query: String)
    }
}