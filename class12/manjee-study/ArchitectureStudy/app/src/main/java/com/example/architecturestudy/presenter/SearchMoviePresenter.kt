package com.example.architecturestudy.presenter

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class SearchMoviePresenter(view: SearchMovieConstract.View) : SearchMovieConstract.Presenter {

    private val movieRepository = MovieRespositoryImpl()

    override fun remoteSearchMovie(
        movieTitle: String
    ) {
        movieRepository.remoteSearchMovie(
            movieTitle,
            success = { movieList -> {} },
            fail = { t ->

            })
    }

}