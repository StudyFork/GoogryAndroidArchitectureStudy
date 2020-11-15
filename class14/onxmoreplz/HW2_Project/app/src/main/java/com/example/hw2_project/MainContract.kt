package com.example.hw2_project

import com.example.hw2_project.data.MovieList

interface MainContract {

    interface View {
        fun showErrorEmptyQuery()
        fun showMovieList(movieList: MovieList)
        fun showErrorRespondMsg(t: Throwable)
    }

    interface Presenter {
        fun requestMovieListToRepo(query: String)
    }

}