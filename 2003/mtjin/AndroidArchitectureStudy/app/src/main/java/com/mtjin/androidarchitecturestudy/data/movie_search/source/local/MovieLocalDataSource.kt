package com.mtjin.androidarchitecturestudy.data.movie_search.source.local

import com.mtjin.androidarchitecturestudy.data.movie_search.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): List<Movie>
    fun getSearchMovies(title: String): List<Movie>
    fun deleteAllMovies()
}