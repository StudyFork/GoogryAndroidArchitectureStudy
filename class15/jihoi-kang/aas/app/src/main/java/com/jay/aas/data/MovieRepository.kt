package com.jay.aas.data

import com.jay.aas.model.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>
    suspend fun getSearchMovies(query: String): List<Movie>

}