package com.example.study.ui.main

import com.example.study.data.model.Movie
import com.example.study.ui.base.BaseContract
import io.reactivex.Single

interface MainContract {

    interface View : BaseContract.View {
        fun showMovieList(items: List<Movie>)
        fun showErrorQueryEmpty()
        fun showErrorEmptyResult()
    }

    interface Presenter : BaseContract.Presenter{
        fun getMovies(query: String)
    }

}