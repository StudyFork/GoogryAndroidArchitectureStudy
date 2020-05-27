package com.lllccww.studyforkproject.ui.main

import com.lllccww.studyforkproject.data.model.MovieItem

interface MainContract {

    interface View {
        fun showMovieList(items: List<MovieItem>)
        fun showFailGetData(msg: String)
        fun showMovieNoResult()
        fun showMovieEmptySearchQuery()
        fun hideKeyboard()
        
    }

    interface Presenter {
        fun getSearchMovie(query: String)
    }
}
