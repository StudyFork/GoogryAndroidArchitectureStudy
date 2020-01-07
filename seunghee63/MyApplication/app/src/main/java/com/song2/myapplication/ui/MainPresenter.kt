package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieRepository

class MainPresenter(
    private val view: MainContract.View,
    private val movieRepository: MovieRepository
) : MainContract.Presenter {
    override fun getMovie() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}