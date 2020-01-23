package com.example.study.ui.main

import com.example.study.data.model.Movie
import com.example.study.util.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun updateMovieList(items: List<Movie>)
        fun showErrorQueryEmpty()
        fun showErrorEmptyResult()
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovies(query: String)
        fun getRecentSearchResult()
    }

}