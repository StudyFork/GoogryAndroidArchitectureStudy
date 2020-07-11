package com.example.architecturestudy.presenter

import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class SearchMoviePresenter(view: SearchMovieConstract.View) : SearchMovieConstract.Presenter {

    private val movieRepository = MovieRespositoryImpl()
    private val searchMovieView = view

    override fun remoteSearchMovie(
        movieTitle: String
    ) {
        movieRepository.remoteSearchMovie(
            movieTitle,
            success = { movieList -> searchMovieView.showMovieList(movieList) },
            fail = { t -> searchMovieView.showSearchFailToast(t) })
    }

}