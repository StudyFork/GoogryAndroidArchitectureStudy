package com.world.tree.architecturestudy.presenter

import com.world.tree.architecturestudy.model.Movie

interface MoviePresenter {
    fun searchMovie(q:String)
    interface View {
        fun showToast(m:String)
        fun setMovieData(list: List<Movie.Item>)
        fun clearList()
    }
}