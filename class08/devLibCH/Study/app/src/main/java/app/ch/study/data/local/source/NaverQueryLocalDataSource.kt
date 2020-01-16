package app.ch.study.data.local.source

import app.ch.study.data.remote.response.MovieModel
import app.ch.study.data.remote.response.MovieResponse

interface NaverQueryLocalDataSource {

    fun searchMovie(): MovieResponse

    fun saveMovieList(movieList: MutableList<MovieModel>)

    fun saveSearchQuery(query: String)

}