package com.jay.aas.data

import com.jay.aas.model.Movie

interface MovieRepository {

    suspend fun getMovies(query: String): List<Movie>

}