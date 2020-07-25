package com.example.architecturestudy.presenter

import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class SearchMoviePresenter(private val view: SearchMovieConstract.View) : SearchMovieConstract.Presenter {

    private val movieRepository = MovieRespositoryImpl()

    override fun searchMovieOnRemote(
        movieTitle: String
    ) {
        movieRepository.searchMovieOnRemote(
            movieTitle,
            success = { movieList -> view.showMovieList(movieList) },
            fail = { t -> view.showSearchFailToast(t) })
    }

}