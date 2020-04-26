package com.mtjin.data.source.search.local

import com.mtjin.data.model.search.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): List<Movie>
    fun getSearchMovies(title: String): List<Movie>
    fun deleteAllMovies()
}