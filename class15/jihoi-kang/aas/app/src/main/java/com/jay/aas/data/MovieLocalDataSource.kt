package com.jay.aas.data

import com.jay.aas.model.Movie

interface MovieLocalDataSource {

    fun insertMovies(movies: List<Movie>)
    fun getMovies(query: String): List<Movie>
    fun clearMovies()

}