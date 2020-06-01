package com.example.architecture.activity.search

import com.example.architecture.data.model.MovieModel

interface SearchContract {

    interface View {
        fun showMovieList(movieList: List<MovieModel>)
        fun showMessageEmptyKeyword()
        fun showMessageEmptyResult()

    }

    interface Presenter {
        fun searchMovie(keyword: String)
        fun clearLocalData(keyword: String)
    }

}