package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.api.MovieModel

interface MovieContract {

    interface View {
        fun showEmptyQuery()
        fun showNoMovieResult()
        fun updateMovieList(list: List<MovieModel.Movie>)
        fun throwError(it: Throwable)

        fun hideKeyboard()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun getMovies(query: String)
        fun clearObservable()
        fun getHistory(): List<String>?
        fun removeHistory(query: String)
    }
}