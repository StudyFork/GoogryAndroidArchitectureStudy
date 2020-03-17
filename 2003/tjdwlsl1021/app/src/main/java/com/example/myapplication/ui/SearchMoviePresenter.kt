package com.example.myapplication.ui

import com.example.myapplication.data.repository.MovieRepositoryDataSet

/**
 * Presenter : 로직, 계산, if문
 * */
class SearchMoviePresenter(private val view: Contract.View, private val movieRepositoryDataSet: MovieRepositoryDataSet): Contract.Presenter {
    private val TAG = "SearchMoviePresenter"

    override fun searchMovie(etMovieTitle:String) {
        if (etMovieTitle.isNotEmpty()) {
            view.showMovieList(etMovieTitle)
        } else {
            view.showToastMovieTitleIsEmpty()
        }
    }

    override fun getMovieList(query: String) {
        movieRepositoryDataSet.movieRepository.getMovieList(
            query,
            success = { view.addItems(it) },
            failed = { view.recordLog(it.toString()) })
    }
}