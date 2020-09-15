package com.hong.architecturestudy.ui.moviedialog

import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface MovieListDialogContract {

    interface View {
        fun loadResentQuery(movieInfo: List<MovieInfo>)
    }

    interface Presenter {
        fun loadResentSearchMovieList()
    }
}