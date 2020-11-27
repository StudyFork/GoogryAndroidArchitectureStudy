package com.jay.aas.ui.movie

import com.jay.aas.base.BaseContract
import com.jay.aas.model.Movie

interface MovieContract {

    interface View : BaseContract.View {

        fun hideKeyboard()
        fun showSearchFailed()
        fun showNoResult()
        fun showMovieItems(movies: List<Movie>)
        fun openMovieDetail(link: String)

    }

    interface Presenter : BaseContract.Presenter {

        suspend fun getMovies()
        suspend fun searchMovies(query: String)
        fun openMovieDetail(link: String)

    }

}