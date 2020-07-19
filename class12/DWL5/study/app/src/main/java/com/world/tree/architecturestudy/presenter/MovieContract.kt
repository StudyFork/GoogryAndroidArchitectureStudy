package com.world.tree.architecturestudy.presenter

import com.world.tree.architecturestudy.model.Movie

interface MovieContract {

    interface Presenter {
        fun searchMovie(q:String)
    }

    interface View {
        fun showToast(m:String)
        fun setMovieData(list: List<Movie.Item>)
        fun clearList()
    }
}