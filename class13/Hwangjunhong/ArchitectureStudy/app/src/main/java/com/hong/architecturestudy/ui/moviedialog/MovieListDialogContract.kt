package com.hong.architecturestudy.ui.moviedialog

import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface MovieListDialogContract {

    interface View {
        fun loadRecentQuery(movieInfo: List<MovieInfo>)
    }

    interface Presenter {
        fun loadRecentSearchMovieList()
    }
}