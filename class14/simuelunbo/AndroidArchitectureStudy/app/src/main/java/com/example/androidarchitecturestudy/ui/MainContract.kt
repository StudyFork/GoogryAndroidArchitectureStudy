package com.example.androidarchitecturestudy.ui

import android.content.Context
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData

interface MainContract {
    interface View {
        fun hideKeyboard()
        fun showLoadingBar()
        fun hideLoadingBar()
        fun showQueryEmpty()
        fun showResultEmpty(error: String)
        fun showResult(movieData: MovieData)
        fun getMovieData(movie: List<Movie>)
        fun openMovieLink(link: String)
    }

    interface Presenter {
        fun saveMovieData(movie: List<Movie>)
        fun requestMovieInfo(title: String)
        fun requestLocalMovieData()
        fun openMovieLink(link: String)
    }
}