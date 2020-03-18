package com.mtjin.androidarchitecturestudy.data.source.local.movie_search

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): List<Movie>
    fun getSearchMovies(title: String): List<Movie>
    fun deleteAllMovies()
}