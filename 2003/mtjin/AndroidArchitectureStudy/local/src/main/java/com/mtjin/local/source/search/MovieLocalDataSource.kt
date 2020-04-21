package com.mtjin.local.source.search

import com.mtjin.local.model.search.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): List<Movie>
    fun getSearchMovies(title: String): List<Movie>
    fun deleteAllMovies()
}