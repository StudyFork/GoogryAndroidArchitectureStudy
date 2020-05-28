package com.lllccww.studyforkproject.ui.main

import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.ui.common.BaseContract

interface MainContract {

    interface View : BaseContract.View {
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
