package com.olaf.nukeolaf.data.local

import com.olaf.nukeolaf.data.model.MovieResponse

interface MovieLocalDataSource {

    fun saveMovies(movies: MovieResponse)
    fun getMovies(): MovieResponse?
}