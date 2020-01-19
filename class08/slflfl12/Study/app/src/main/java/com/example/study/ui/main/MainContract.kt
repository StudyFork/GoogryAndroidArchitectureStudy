package com.example.study.ui.main

import com.example.study.data.model.Movie
import io.reactivex.Single

interface MainContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showMovieList(items: Single<Movie>)
        fun showErrorQueryEmpty()
        fun showErrorEmptyResult()
    }

    interface Presenter {
        fun getMovies(query: String): Single<Movie>
    }

}