package com.example.androidarchitecturestudy.ui.base

import com.example.androidarchitecturestudy.data.model.Movie

interface BaseContract {
    interface View {
        fun showLoadingBar()
        fun hideLoadingBar()
        fun showResultEmpty(error: String)
        fun getMovieData(movie: List<Movie>)
    }

    interface Presenter {
        fun saveMovieData(movie: List<Movie>)
        fun requestLocalMovieData()
    }
}