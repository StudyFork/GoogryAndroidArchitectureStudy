package com.example.architecture.activity.search

import androidx.annotation.StringRes
import com.example.architecture.data.model.MovieModel

interface SearchContract {

    interface View {
        fun showMovieList(movieList: List<MovieModel>)
        fun showMessageEmptyKeyword()
        fun showMessageEmptyResult()

    }

    interface Presenter {
        fun searchMovie(keyword: String)
        fun searchMovie(actionId: Int, keyword: String): Boolean
        fun isValidKeyword(keyword: String): Boolean
        fun clearLocalData(keyword: String)
    }

}