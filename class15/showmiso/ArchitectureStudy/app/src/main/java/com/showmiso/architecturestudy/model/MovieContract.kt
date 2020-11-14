package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

interface MovieContract {

    interface View {
        fun showEmptyQuery()
        fun showNoMovieResult()
        fun showMovieList()
        fun updateMovieList(list: List<MovieModel.Movie>)

        fun showKeyboard()
        fun hideKeyboard()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun getMovies(query: String)
        fun clear()
    }
}