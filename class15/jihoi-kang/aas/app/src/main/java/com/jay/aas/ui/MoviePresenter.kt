package com.jay.aas.ui

import com.jay.aas.data.MovieRepository

class MoviePresenter(
    private val view: MovieContract.View,
    private val movieRepository: MovieRepository,
) : MovieContract.Presenter {

    override suspend fun getMovies() {
        val movies = movieRepository.getMovies()

        if (movies.isEmpty()) {
            view.showNoResult()
        } else {
            view.showMovieItems(movies)
        }
    }

    override suspend fun searchMovies(query: String) {
        if (query.isEmpty()) return

        view.hideKeyboard()
        try {
            view.showProgress()
            val movies = movieRepository.getSearchMovies(query)
            view.hideProgress()

            if (movies.isEmpty()) {
                view.showNoResult()
            } else {
                view.showMovieItems(movies)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            view.hideProgress()
            view.showSearchFailed()
        }
    }
}