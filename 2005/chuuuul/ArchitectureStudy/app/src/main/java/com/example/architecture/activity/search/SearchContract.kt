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
        fun isValidKeyword(keyword: String): Boolean
        fun clearLocalData(keyword: String)
    }

}