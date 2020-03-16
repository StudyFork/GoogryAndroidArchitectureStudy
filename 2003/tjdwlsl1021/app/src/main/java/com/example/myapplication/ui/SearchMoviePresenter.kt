package com.example.myapplication.ui

import com.example.myapplication.data.local.MovieEntity

/**
 * Presenter : 로직, 계산, if문
 * */
class SearchMoviePresenter(private val view: Contract.View): Contract.Presenter {

    override fun checkIsEmptyMovieTitle(etMovieTitle:String) {
        if (etMovieTitle.isNotEmpty()) {
            view.showMovieList(etMovieTitle)
        } else {
            view.showToastMovieTitleIsEmpty()
        }
    }

    override fun goToMovieWebPage(it: MovieEntity) {
        view.showMoiveWebPage(it)
    }

}