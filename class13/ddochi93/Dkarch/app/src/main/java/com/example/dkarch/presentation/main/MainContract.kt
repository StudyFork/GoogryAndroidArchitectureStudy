package com.example.dkarch.presentation.main

import com.example.dkarch.data.entity.Movie
import com.example.dkarch.presentation.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun showMovieList(movieList: ArrayList<Movie>)

        fun showEmptyMessage()
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovieList(query: String)

        fun getQueryList(): List<String>

        fun handleError(throwable: Throwable)
    }
}
