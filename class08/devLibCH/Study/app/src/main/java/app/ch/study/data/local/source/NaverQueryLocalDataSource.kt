package app.ch.study.data.local.source

import app.ch.study.data.remote.response.MovieModel

interface NaverQueryLocalDataSource {

    fun searchMovie(): MutableList<MovieModel>

    fun saveMovieList(movieList: MutableList<MovieModel>)

}