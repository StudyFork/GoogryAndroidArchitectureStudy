package com.world.tree.architecturestudy.presenter

import com.world.tree.architecturestudy.model.Movie

interface MoviePresenter {
    fun searchMovie():List<Movie>
}