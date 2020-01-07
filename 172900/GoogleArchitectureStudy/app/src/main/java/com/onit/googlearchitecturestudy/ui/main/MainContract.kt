package com.onit.googlearchitecturestudy.ui.main

import android.widget.Toast
import com.onit.googlearchitecturestudy.Movie

interface MainContract {

    interface View {
        fun hideKeyBoard()
        fun showLoadingProgressBar()
        fun hideLoadingProgressBar()
        fun showToastMessage(message: String, option: Int = Toast.LENGTH_SHORT)
        fun setMovieList(movieList: List<Movie>)
    }

    interface Presenter {
        fun searchMovies(keyword: String)
    }
}