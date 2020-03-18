package com.mtjin.androidarchitecturestudy.data.search.source.local

import com.mtjin.androidarchitecturestudy.data.search.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): List<Movie>
    fun getSearchMovies(title: String): List<Movie>
    fun deleteAllMovies()
}