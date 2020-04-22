package com.olaf.nukeolaf.ui

import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.data.repository.MovieRepository

class MainPresenter(
    private val movieRepository: MovieRepository,
    private val view: MainContract.View
) : MainContract.Presenter {


    override fun loadMovies() {
        val movieList = movieRepository.getMovies()
        if (movieList != null && movieList.items.isNotEmpty()) {
            view.showMovies(movieList.items)
        }
    }

    override fun searchMovie(query: String?) {
        if (query == null) {
            view.showEmptySearchWord()
            return
        }
        movieRepository.searchMovies(
            query,
            object : MovieRepository.LoadMoviesCallback {
                override fun onMoviesLoaded(movieResponse: MovieResponse) {
                    if (movieResponse.items.isNotEmpty()) {
                        view.showMovies(movieResponse.items)
                    } else {
                        view.showNoResultForSearchWord(query)
                    }
                }

                override fun onResponseError(message: String) {
                    view.showServerError()
                }

                override fun onFailure(t: Throwable) {
                    view.showServerError()
                }
            })
    }
}