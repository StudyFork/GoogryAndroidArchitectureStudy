package com.example.architecturestudy.ui

import com.example.architecturestudy.ui.movie.MovieFragment

class MainPresenter(val view : MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.showFragment(MovieFragment())
    }
}